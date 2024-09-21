package com.didan.elearning.materials.service;

import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.ClassMaterialResponseDto;
import com.didan.elearning.materials.entity.ClassMaterials;
import java.util.List;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.multipart.MultipartFile;

public interface IClassMaterialService {
    ClassMaterialResponseDto createClassMaterial(ClassMaterialRequestDto classMaterialRequestDto);
    byte[] downloadMaterial(String fileName, String path);
    ClassMaterialResponseDto findClassMaterialByTitleAndClassCode(String fileName, String classCode);
    String getUrlMaterial(String path);
    List<ClassMaterials> findAllMaterialsInClass(String classCode);
    void deleteMaterial(String fileName, String classCode);
    void deleteAllMaterialsInClass(String classCode);
    void deleteMultipleMaterials(List<String> fileNames, String classCode);
    void copyFileToOtherClassCode(List<String> fileNames, String srcClassCode, String dstClassCode);
    void moveFilesToOtherClassCode(List<String> fileNames, String srcClassCode, String dstClassCode);
}
