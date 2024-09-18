package com.didan.elearning.materials.service;

import org.springframework.web.multipart.MultipartFile;

public interface IMinioService {
  void createClassFolder(String folderName);
  void uploadClassMaterial(MultipartFile file, String folderName);
  void deleteMaterial(String fileName);
  void deleteAllMaterialsInFolder(String folderName);
  void getInfoMaterial(String fileName);
  void getMaterialStatus(String fileName);
  void getUrlMaterial(String fileName);
  void downloadMaterial(String fileName);
}
