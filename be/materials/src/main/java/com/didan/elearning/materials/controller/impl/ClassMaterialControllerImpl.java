package com.didan.elearning.materials.controller.impl;

import com.didan.elearning.materials.controller.IClassMaterialController;
import com.didan.elearning.materials.dto.request.ClassMaterialActionRequestDto;
import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.ClassMaterialResponseDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.entity.ClassMaterials;
import com.didan.elearning.materials.service.IClassMaterialService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassMaterialControllerImpl implements IClassMaterialController {
  private final IClassMaterialService classMaterialService;
  @Override
  public ResponseEntity<GeneralResponse<ClassMaterialResponseDto>> createClassMaterial(
      ClassMaterialRequestDto requestDto) {
    log.info("Create class material: {}", requestDto);
    ClassMaterialResponseDto classMaterials = classMaterialService.createClassMaterial(requestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Created class material successfully", classMaterials), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<byte[]> downloadMaterial(String fileName, String classCode) {
    log.info("Download material: {}", fileName);
    ClassMaterialResponseDto classMaterials = classMaterialService.findClassMaterialByTitleAndClassCode(fileName, classCode);
    byte[] file = classMaterialService.downloadMaterial(classMaterials.getTitle(), classMaterials.getPath());
    return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType(classMaterials.getMaterialType()))
        .body(file);
  }

  @Override
  public ResponseEntity<GeneralResponse<String>> getUrl(String fileName, String classCode) {
    log.info("Get url material: {}", fileName);
    ClassMaterialResponseDto classMaterials = classMaterialService.findClassMaterialByTitleAndClassCode(fileName, classCode);
    String url = classMaterialService.getUrlMaterial(classMaterials.getPath());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get url material successfully", url), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<ClassMaterialResponseDto>> getClassMaterial(String fileName, String classCode) {
    log.info("Get class material: {}", fileName);
    ClassMaterialResponseDto classMaterials = classMaterialService.findClassMaterialByTitleAndClassCode(fileName, classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get class material successfully", classMaterials), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ClassMaterials>>> getAllMaterialsInClass(String classCode) {
    log.info("Get all materials in class: {}", classCode);
    List<ClassMaterials> classMaterials = classMaterialService.findAllMaterialsInClass(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Get all materials in class successfully", classMaterialService.findAllMaterialsInClass(classCode)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteMaterial(String fileName, String classCode) {
    log.info("Delete material: {}", fileName);
    classMaterialService.deleteMaterial(fileName, classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Delete material successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteAllMaterialsInClass(String classCode) {
    log.info("Delete all materials in class: {}", classCode);
    classMaterialService.deleteAllMaterialsInClass(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Delete all materials in class successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteMultipleMaterials(List<String> fileNames,
      String classCode) {
    log.info("Delete multiple materials: {}", fileNames);
    classMaterialService.deleteMultipleMaterials(fileNames, classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Delete multiple materials successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> copyFile(ClassMaterialActionRequestDto requestDto) {
    log.info("Copy file to other class code: {}", requestDto);
    classMaterialService.copyFileToOtherClassCode(requestDto.getFileName(), requestDto.getSrcClassCode(), requestDto.getDestClassCode());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Copy file to other class code successfully", null), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> cutFile(ClassMaterialActionRequestDto requestDto) {
    log.info("Cut file to other class code: {}", requestDto);
    classMaterialService.moveFilesToOtherClassCode(requestDto.getFileName(), requestDto.getSrcClassCode(), requestDto.getDestClassCode());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Cut file to other class code successfully", null), HttpStatus.OK);
  }
}
