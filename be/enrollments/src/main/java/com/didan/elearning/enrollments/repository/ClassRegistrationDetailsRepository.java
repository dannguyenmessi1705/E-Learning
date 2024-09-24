package com.didan.elearning.enrollments.repository;

import com.didan.elearning.enrollments.entity.ClassRegistrationDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRegistrationDetailsRepository extends
    JpaRepository<ClassRegistrationDetails, String> {

  List<ClassRegistrationDetails> findClassRegistrationDetailsByClassCodeIgnoreCase(
      String classCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsBySemesterCodeIgnoreCase(
      String semesterCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsByCourseCodeIgnoreCase(
      String courseCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndCourseCodeIgnoreCaseAndClassCodeIgnoreCase(
      String semesterCode, String courseCode, String classCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndCourseCodeIgnoreCase(
      String semesterCode, String courseCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsBySemesterCodeIgnoreCaseAndClassCodeIgnoreCase(
      String semesterCode, String classCode);

  List<ClassRegistrationDetails> findClassRegistrationDetailsByCourseCodeIgnoreCaseAndClassCodeIgnoreCase(
      String courseCode, String classCode);

  Optional<ClassRegistrationDetails> findClassRegistrationDetailsByClassCodeAndCourseCodeAndSemesterCode(
      String classCode, String courseCode, String semesterCode);
}
