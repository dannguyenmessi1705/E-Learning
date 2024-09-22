package com.didan.elearning.courses.constants;

public class MessageConstants {
  private MessageConstants() {}
  public static final String SEMESTER_CREATED = "Semester created successfully";
  public static final String SEMESTER_ALREADY_EXISTS = "Semester with code %s already exists";
  public static final String SEMESTER_NOT_FOUND = "Semester with code %s not found";
  public static final String COURSE_CREATED = "Course created successfully";
  public static final String COURSE_ALREADY_EXISTS = "Course with code %s already exists";
  public static final String COURSE_NOT_FOUND = "Course with code %s not found";
  public static final String NO_COURSES_FOUND_FOR_SEMESTER = "No courses found for semester with code %s";
  public static final String CLASS_NOT_FOUND = "Class with code %s not found";
  public static final String STUDENT_NOT_FOUND_IN_CLASS = "Student with code %s not found in class with code %s";
  public static final String STUDENT_NOT_FOUND = "Student with code %s not found";
  public static final String INSTRUCTOR_NOT_FOUND = "Instructor with id %s not found";
  public static final String ASSISTANT_NOT_FOUND = "Assistant with id %s not found";

  public static class Status {
    private Status() {}
    public static final Integer SUCCESS = 200;
    public static final Integer NOT_FOUND = 404;
  }
}
