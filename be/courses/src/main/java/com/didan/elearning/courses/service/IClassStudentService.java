package com.didan.elearning.courses.service;

import com.didan.elearning.courses.dto.request.ClassStudentRequestDto;
import com.didan.elearning.courses.dto.response.ClassResponseDto;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;
import java.util.List;

public interface IClassStudentService {
    void addStudentsToClass(ClassStudentRequestDto classStudentRequestDto);
    void removeStudentsFromClass(ClassStudentRequestDto classStudentRequestDto);
    void removeAllStudentsFromClass(String classCode);
    void removeAllClassesOfStudent(String studentCode);
    List<ClassResponseDto> getClassesOfStudent(String studentCode);
    List<UpdateUserDetailResponseDto> getStudentsInClass(String classCode);
}
