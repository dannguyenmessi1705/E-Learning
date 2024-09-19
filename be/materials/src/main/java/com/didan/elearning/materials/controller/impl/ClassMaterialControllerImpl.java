package com.didan.elearning.materials.controller.impl;

import com.didan.elearning.materials.controller.IClassMaterialController;
import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.entity.ClassMaterials;
import com.didan.elearning.materials.service.IClassMaterialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ClassMaterialControllerImpl implements IClassMaterialController {
  private final IClassMaterialService classMaterialService;
  @Override
  public ResponseEntity<GeneralResponse<ClassMaterials>> createClassMaterial(
      ClassMaterialRequestDto requestDto) {
    log.info("Create class material: {}", requestDto);
    ClassMaterials classMaterials = classMaterialService.createClassMaterial(requestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Created class material successfully", classMaterials), HttpStatus.CREATED);
  }
}
