package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.request.ClassRequestDto;
import com.didan.elearning.courses.dto.request.ClassUpdateRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.entity.CourseClasses;
import java.util.List;

public interface ICourseClassesService {
    List<ClassResponseDto> getAllClassesOfCourse(String courseCode);
    List<ClassResponseDto> getAllClassesByInstructor(String instructorId);
    List<ClassResponseDto> getAllClassesByAssistant(String assistantId);
    ClassResponseDto createClassOfCourse(ClassRequestDto classRequestDto);
    ClassResponseDto getClassByCode(String classCode);
    ClassResponseDto updateCourseClasses(ClassUpdateRequestDto classUpdateRequestDto);
    void deleteCourseClasses(String classCode);
}
