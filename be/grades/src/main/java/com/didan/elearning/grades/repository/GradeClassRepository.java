package com.didan.elearning.grades.repository;

import com.didan.elearning.grades.entity.GradeClass;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeClassRepository extends JpaRepository<GradeClass, String> {
  Optional<GradeClass> findGradeClassByClassCodeIgnoreCase(String classCode);
}
