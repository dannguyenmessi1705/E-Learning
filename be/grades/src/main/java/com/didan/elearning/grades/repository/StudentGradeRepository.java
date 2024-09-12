package com.didan.elearning.grades.repository;

import com.didan.elearning.grades.entity.StudentGrade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGrade, String> {
  List<StudentGrade> findStudentGradeByStudentCodeIgnoreCase(String studentCode);
  List<StudentGrade> findStudentGradeByGradeClass_ClassCodeIgnoreCase(String classCode);
  Optional<StudentGrade> findStudentGradeByStudentCodeAndGradeClass_ClassCodeIgnoreCase(String studentCode, String classCode);
}
