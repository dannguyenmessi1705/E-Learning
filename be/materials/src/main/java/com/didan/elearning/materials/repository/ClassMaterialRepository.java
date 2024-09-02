package com.didan.elearning.materials.repository;

import com.didan.elearning.materials.entity.ClassMaterials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassMaterialRepository extends JpaRepository<ClassMaterials, String> {

}
