package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.ClassStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, String> {

}
