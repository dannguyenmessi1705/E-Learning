package com.didan.elearning.grades.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class StudentGrade extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String studentGradeId;
  @Column
  private Double attendanceScore;
  @Column
  private Double assignmentScore;
  @Column
  private Double midtermScore;
  @Column
  private Double practiceScore;
  @Column
  private Double finalScore;
  @Column
  private Double totalScore;
  @Column
  private String studentCode;
  @Column
  private String typeGrade; // A, B, C, D, E, F (TypeGradeConstants)
  @ManyToOne
  @JoinColumn(name = "gradeClassCourseId")
  private GradeClassCourse gradeClassCourse;
}
