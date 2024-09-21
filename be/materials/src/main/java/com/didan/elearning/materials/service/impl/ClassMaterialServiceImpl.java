package com.didan.elearning.materials.service.impl;

import com.didan.elearning.materials.constant.MessageConstant;
import com.didan.elearning.materials.constant.MessageConstant.Status;
import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.ClassInDateResponseDto;
import com.didan.elearning.materials.dto.response.ClassMaterialResponseDto;
import com.didan.elearning.materials.dto.response.ClassResponseDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.dto.response.TimeClassResponseDto;
import com.didan.elearning.materials.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.materials.entity.ClassMaterials;
import com.didan.elearning.materials.exception.ErrorActionException;
import com.didan.elearning.materials.exception.ResourceNotFoundException;
import com.didan.elearning.materials.repository.ClassMaterialRepository;
import com.didan.elearning.materials.service.IClassMaterialService;
import com.didan.elearning.materials.service.client.CoursesFeignClient;
import com.didan.elearning.materials.service.client.ClassInDateFeignClient;
import com.didan.elearning.materials.service.client.UserFeignClient;
import com.didan.elearning.materials.utils.ExtractErrorMessageUtils;
import com.didan.elearning.materials.utils.MapperUtils;
import com.didan.elearning.materials.utils.MinioUtils;
import io.minio.StatObjectResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassMaterialServiceImpl implements IClassMaterialService {
  private final ClassMaterialRepository classMaterialRepository;
  private final MinioUtils minioUtils;
  private final CoursesFeignClient coursesFeignClient;
  private final ClassInDateFeignClient classInDateFeignClient;
  private final UserFeignClient userFeignClient;

  @Value("${minio.bucket-name}")
  private String bucketName;
  @Value("${minio.endpoint}")
  private String minioEndpoint;

  @Override
  public ClassMaterialResponseDto createClassMaterial(ClassMaterialRequestDto requestDto) {
    try {
      ResponseEntity<GeneralResponse<ClassResponseDto>> classCodes = coursesFeignClient.getClassByCode(requestDto.getClassCode());
      if (!Objects.equals(Objects.requireNonNull(classCodes.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(String.format(MessageConstant.CLASS_NOT_FOUND, requestDto.getClassCode()));
      }

      ResponseEntity<GeneralResponse<ClassInDateResponseDto>> timeClass = classInDateFeignClient.getClassInDateById(requestDto.getTimeClassInDateId());
      if (!Objects.equals(Objects.requireNonNull(timeClass.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(
            String.format(MessageConstant.CLASS_TIME_NOT_FOUND, requestDto.getTimeClassInDateId()));
      }

      ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> user = userFeignClient.getUserDetails(requestDto.getInstructorId());
      if (!Objects.equals(Objects.requireNonNull(user.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(ExtractErrorMessageUtils.extractErrorMessage(user.getBody().getMessage()));
      }

    } catch (Exception e) {
      throw new ResourceNotFoundException(ExtractErrorMessageUtils.extractErrorMessage(e.getMessage()));
    }
    
    minioUtils.createBucket(bucketName);
    String fileName = requestDto.getFile().getOriginalFilename();
    if (!StringUtils.hasText(fileName)) {
      throw new ErrorActionException("File name is empty");
    }
    String newFileName = fileName.split("\\.")[0] + "_" + System.currentTimeMillis() + "." + fileName.split(
        "\\.")[1];
    String contentType = requestDto.getFile().getContentType();
    String path = requestDto.getClassCode() + "/" + newFileName;
    minioUtils.uploadFile(bucketName, requestDto.getFile(), path, contentType);
    log.info("File uploaded: {}", path);

    ClassMaterials classMaterials = MapperUtils.map(requestDto, ClassMaterials.class);
    classMaterials.setMaterialType(contentType);
    classMaterials.setTitle(newFileName);
    classMaterials.setPath(path);
    classMaterialRepository.save(classMaterials);
    log.info("Class material saved: {}", classMaterials);
    StatObjectResponse statFile = minioUtils.getFileStatusInfo(bucketName, classMaterials.getPath());
    ClassMaterialResponseDto responseDto = MapperUtils.map(classMaterials, ClassMaterialResponseDto.class);
    responseDto.setSize(String.valueOf(statFile.size()));
    return responseDto;
  }

  @Override
  public byte[] downloadMaterial(String fileName, String path) {
    log.info("Downloading...");
    InputStream stream = minioUtils.getObject(bucketName, path);
    if (stream == null) {
      log.error("File not found: {}", fileName);
      throw new ResourceNotFoundException(String.format(MessageConstant.FILE_NOT_FOUND, fileName));
    }
    return convertInputStreamToByteArray(stream);
  }

  private String cutUrl(String url) {
    int statIndex = url.indexOf("/", url.indexOf("//") + 2);
    if (statIndex != -1) {
      return url.substring(statIndex + 1);
    }
    return url;
  }

  @Override
  public ClassMaterialResponseDto findClassMaterialByTitleAndClassCode(String title, String classCode) {
    verifyClassCodeExist(classCode);
    ClassMaterials classMaterials = classMaterialRepository.findClassMaterialsByTitleAndClassCode(title, classCode)
        .orElseThrow(() -> {
          log.error("File not found: {}", title);
          return new ResourceNotFoundException(String.format(MessageConstant.FILE_NOT_FOUND, title));
        });
    StatObjectResponse statFile = minioUtils.getFileStatusInfo(bucketName, classMaterials.getPath());
    ClassMaterialResponseDto responseDto = MapperUtils.map(classMaterials, ClassMaterialResponseDto.class);
    responseDto.setSize(String.valueOf(statFile.size()));
    return responseDto;
  }

  private byte[] convertInputStreamToByteArray(InputStream stream) {
    try {
      return IOUtils.toByteArray(stream);
    } catch (IOException e) {
      log.error("Convert input stream to byte array error: {}", e.getMessage());
      return new byte[0];
    }
  }

  @Override
  public String getUrlMaterial(String path) {
    String url = minioUtils.getPresignedObjectUrl(bucketName, path);
    return minioUtils.getUTF8ByURLDecoder(url);
  }

  @Override
  public List<ClassMaterials> findAllMaterialsInClass(String classCode) {
    verifyClassCodeExist(classCode);
    List<ClassMaterials> classMaterials = classMaterialRepository.findAllByClassCode(classCode);
    if (classMaterials.isEmpty()) {
      log.info("Materials not found in class: {}", classCode);
      return List.of();
    }
    return classMaterials;
  }

  private void verifyClassCodeExist(String classCode) {
    try {
      ResponseEntity<GeneralResponse<ClassResponseDto>> classCodes = coursesFeignClient.getClassByCode(
          classCode);
      if (!Objects.equals(Objects.requireNonNull(classCodes.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(String.format(MessageConstant.CLASS_NOT_FOUND,
            classCode));
      }
    } catch (Exception e) {
      throw new ResourceNotFoundException(ExtractErrorMessageUtils.extractErrorMessage(e.getMessage()));
    }
  }

  @Override
  @Transactional
  @Modifying
  public void deleteMaterial(String fileName, String classCode) {
    verifyClassCodeExist(classCode);
    ClassMaterialResponseDto classMaterials = findClassMaterialByTitleAndClassCode(fileName, classCode);
    minioUtils.removeFile(bucketName, classMaterials.getPath());
    classMaterialRepository.deleteById(classMaterials.getMaterialId());
    log.info("File deleted: {}", fileName);
  }

  @Override
  public void deleteAllMaterialsInClass(String classCode) {
    verifyClassCodeExist(classCode);
    List<ClassMaterials> classMaterials = findAllMaterialsInClass(classCode);
    if (classMaterials.isEmpty()) {
      log.info("Materials not found in class: {}", classCode);
      return;
    }
    classMaterials.forEach(classMaterial -> {
      minioUtils.removeFile(bucketName, classMaterial.getPath());
      classMaterialRepository.delete(classMaterial);
      log.info("File deleted: {}", classMaterial.getTitle());
    });
  }

  @Override
  @Transactional
  @Modifying
  public void deleteMultipleMaterials(List<String> fileNames, String classCode) {
    verifyClassCodeExist(classCode);
    List<ClassMaterials> classMaterials = classMaterialRepository.findAllByClassCode(classCode);
    if (classMaterials.isEmpty()) {
      log.info("Materials not found in class: {}", classCode);
      return;
    }
    for (String fileName : fileNames) {
      ClassMaterials classMaterial = classMaterialRepository.findClassMaterialsByTitleAndClassCode(fileName, classCode).orElse(null);
      if (classMaterial != null) {
        minioUtils.removeFile(bucketName, classMaterial.getPath());
        classMaterialRepository.delete(classMaterial);
        log.info("File deleted: {}", fileName);
      }
    }
  }

  @Override
  public void copyFileToOtherClassCode(List<String> fileNames, String srcClassCode, String dstClassCode) {
    permitActionWithFile(srcClassCode, dstClassCode);
    for (String fileName : fileNames) {
      ClassMaterialResponseDto responseDto = findClassMaterialByTitleAndClassCode(fileName, srcClassCode);
      InputStream stream = minioUtils.getObject(bucketName, responseDto.getPath());
      minioUtils.uploadFile(bucketName, dstClassCode + "/" + responseDto.getTitle(), stream);
      ClassMaterials classMaterials = ClassMaterials.builder()
          .classCode(dstClassCode)
          .materialType(responseDto.getMaterialType())
          .description(responseDto.getDescription())
          .timeClassInDateId(responseDto.getTimeClassInDateId())
          .instructorId(responseDto.getInstructorId())
          .path(dstClassCode + "/" + responseDto.getTitle())
          .title(responseDto.getTitle())
          .build();
      classMaterialRepository.save(classMaterials);
      log.info("File copied: {}", fileName);
    }
  }

  @Override
  public void moveFilesToOtherClassCode(List<String> fileNames, String srcClassCode,
      String dstClassCode) {
    permitActionWithFile(srcClassCode, dstClassCode);
    for (String fileName : fileNames) {
      ClassMaterialResponseDto responseDto = findClassMaterialByTitleAndClassCode(fileName,
          srcClassCode);
      InputStream stream = minioUtils.getObject(bucketName, responseDto.getPath());
      minioUtils.uploadFile(bucketName, dstClassCode + "/" + responseDto.getTitle(), stream);
      ClassMaterials classMaterials = ClassMaterials.builder()
          .classCode(dstClassCode)
          .materialType(responseDto.getMaterialType())
          .description(responseDto.getDescription())
          .timeClassInDateId(responseDto.getTimeClassInDateId())
          .instructorId(responseDto.getInstructorId())
          .path(dstClassCode + "/" + responseDto.getTitle())
          .title(responseDto.getTitle())
          .build();
      classMaterialRepository.save(classMaterials);
      minioUtils.removeFile(bucketName, responseDto.getPath());
      classMaterialRepository.deleteById(responseDto.getMaterialId());
      log.info("File moved: {}", fileName);
    }
  }

  private void permitActionWithFile(String srcClassCode, String dstClassCode) {
    try {
      ResponseEntity<GeneralResponse<ClassResponseDto>> srcClass = coursesFeignClient.getClassByCode(
          srcClassCode);
      if (!Objects.equals(Objects.requireNonNull(srcClass.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(String.format(MessageConstant.CLASS_NOT_FOUND,
            srcClassCode));
      }
      ResponseEntity<GeneralResponse<ClassResponseDto>> dstClass = coursesFeignClient.getClassByCode(
          dstClassCode);
      if (!Objects.equals(Objects.requireNonNull(dstClass.getBody()).getStatusCode(), Status.SUCCESS)) {
        throw new ResourceNotFoundException(String.format(MessageConstant.CLASS_NOT_FOUND,
            dstClassCode));
      }
      if (!Objects.equals(Objects.requireNonNull(srcClass.getBody()).getData().getInstructorId(),
          Objects.requireNonNull(dstClass.getBody()).getData().getInstructorId())) {
        throw new ErrorActionException(MessageConstant.INSTRUCTION_NOT_MATCH);
      }
    } catch (Exception e) {
      throw new ResourceNotFoundException(ExtractErrorMessageUtils.extractErrorMessage(e.getMessage()));
    }
  }
}
