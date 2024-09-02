package com.didan.elearning.grades.repository;

import com.didan.elearning.grades.entity.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, String> {

}
