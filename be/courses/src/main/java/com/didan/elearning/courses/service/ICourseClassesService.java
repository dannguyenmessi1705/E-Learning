package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.entity.CourseClasses;
import java.util.List;

public interface ICourseClassesService {
    List<ClassResponseDto> getAllClassesOfCourse(String courseCode);
    List<CourseClasses> getAllClassesByInstructor(String instructorId);
    List<CourseClasses> getAllClassesByAssistant(String assistantId);
    ClassResponseDto createClassOfCourse(ClassRequestDto classRequestDto);
    CourseClasses getClassByCode(String classCode);
    void addClassToCourse(String courseCode, CourseClasses courseClasses);
    void updateCourseClasses(CourseClasses courseClasses);
    void deleteCourseClasses(String classCode);
}
