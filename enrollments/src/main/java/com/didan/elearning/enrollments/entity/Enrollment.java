package com.didan.elearning.enrollments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Enrollment extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String enrollmentId;
  @Column
  private String classCode;
  @Column
  private String semesterCode;
  @Column
  private String studentId;
  @Column
  private String status; // EnrollmentStatusConstant
}
