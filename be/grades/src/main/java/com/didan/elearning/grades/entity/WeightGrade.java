package com.didan.elearning.grades.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class WeightGrade extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String weightGradeId;
  @Column
  private Double attendanceWeight;
  @Column
  private Double assignmentWeight;
  @Column
  private Double midtermWeight;
  @Column
  private Double practiceWeight;
  @Column
  private Double finalWeight;
  @OneToMany(mappedBy = "weightGrade", fetch = FetchType.LAZY)
  private List<GradeClassCourse> gradeClassCourses;
}
