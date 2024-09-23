package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.ClassStudents;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, String> {
  Optional<ClassStudents> findClassStudentsByStudentCodeAndCourseClasses_ClassCode(String studentCode, String classCode);
  List<ClassStudents> findClassStudentsByCourseClasses_ClassCode(String classCode);
  @Transactional
  @Modifying
  void deleteAllByCourseClasses_ClassCode(String classCode);
  @Transactional
  @Modifying
  void deleteAllByStudentCode(String studentCode);
  List<ClassStudents> findClassStudentsByStudentCodeIgnoreCase(String studentCode);
}
