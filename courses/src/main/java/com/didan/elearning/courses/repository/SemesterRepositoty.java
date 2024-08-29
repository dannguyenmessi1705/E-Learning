package com.didan.elearning.courses.repository;

import com.didan.elearning.courses.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepositoty extends JpaRepository<Semester, String> {

}
