package com.didan.elearning.materials.repository;

import com.didan.elearning.materials.entity.ClassMaterials;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ClassMaterialRepository extends JpaRepository<ClassMaterials, String> {
  Optional<ClassMaterials> findClassMaterialsByTitleAndClassCode(String title, String classCode);
  List<ClassMaterials> findAllByClassCode(String classCode);

  @Transactional
  @Modifying
  void deleteAllByClassCode(String classCode);
}
