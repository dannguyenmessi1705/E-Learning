package com.didan.elearning.materials.service.impl;

import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.entity.ClassMaterials;
import com.didan.elearning.materials.exception.ErrorActionException;
import com.didan.elearning.materials.repository.ClassMaterialRepository;
import com.didan.elearning.materials.service.IClassMaterialService;
import com.didan.elearning.materials.utils.MapperUtils;
import com.didan.elearning.materials.utils.MinioUtils;
import io.minio.ObjectWriteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassMaterialServiceImpl implements IClassMaterialService {
  private final ClassMaterialRepository classMaterialRepository;
  private final MinioUtils minioUtils;

  @Value("${minio.bucket-name}")
  private String bucketName;

  @Override
  public ClassMaterials createClassMaterial(ClassMaterialRequestDto requestDto) {
    /**
     * OpenFeign call to course service to get class by classCode
     * Check if class is existed
     * If class isn't exist, throw exception
     *
     * OpenFeign call to timetable service to get TimeClassInDate by TimeClassInDateId
     * Check if TimeClassInDate is existed
     * If TimeClassInDate isn't exist, throw exception
     *
     * OpenFeign call to user service to get user by instructorId
     * Check if user is existed
     * If user isn't exist, throw exception
     */
    minioUtils.createBucket(bucketName);
//    if (!minioUtils.isFolderExists(bucketName, requestDto.getClassCode() + "/")) {
//      ObjectWriteResponse newFolder = minioUtils.createDir(bucketName, requestDto.getClassCode() + "/");
//      log.info("Folder created: {}", newFolder.object());
//    }
    String fileName = requestDto.getFile().getOriginalFilename();
    if (!StringUtils.hasText(fileName)) {
      throw new ErrorActionException("File name is empty");
    }
    String newFileName = fileName.split("\\.")[0] + "_" + System.currentTimeMillis() + "." + fileName.split(
        "\\.")[1];
    String contentType = requestDto.getFile().getContentType();
    minioUtils.uploadFile(bucketName, requestDto.getFile(), requestDto.getClassCode() + "/" + newFileName, contentType);
    log.info("File uploaded: {}", requestDto.getClassCode() + "/" + newFileName);

    ClassMaterials classMaterials = MapperUtils.map(requestDto, ClassMaterials.class);
    classMaterials.setMaterialType(contentType);
    classMaterials.setTitle(newFileName);
    String url = cutUrl(minioUtils.getPresignedObjectUrl(bucketName, requestDto.getClassCode() + "/" + newFileName));
    classMaterials.setUrl(url);
    classMaterialRepository.save(classMaterials);
    log.info("Class material saved: {}", classMaterials);
    return classMaterials;
  }

  private String cutUrl(String url) {
    int statIndex = url.indexOf("/", url.indexOf("//") + 2);
    if (statIndex != -1) {
      return url.substring(statIndex + 1);
    }
    return url;
  }
}
