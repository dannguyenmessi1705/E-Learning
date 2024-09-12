package com.didan.elearning.grades.repository;

import com.didan.elearning.grades.entity.WeightGrade;
import java.util.Optional;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightGradeRepository extends JpaRepository<WeightGrade, String> {
  Optional<WeightGrade> findWeightGradeByCourseCodeIgnoreCase(String courseCode);
}
