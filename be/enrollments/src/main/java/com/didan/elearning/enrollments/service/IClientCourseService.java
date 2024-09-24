package com.didan.elearning.enrollments.service;

public interface IClientCourseService {
    boolean checkClassExisted(String classCode, String courseCode, String semesterCode);
}
