package com.didan.elearning.materials.service;

import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.entity.ClassMaterials;
import org.springframework.web.multipart.MultipartFile;

public interface IClassMaterialService {
    ClassMaterials createClassMaterial(ClassMaterialRequestDto classMaterialRequestDto);
}
