package com.didan.elearning.grades.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class GradeClassCourse extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String gradeClassCourseId;
  @Column
  private String classCode;
  @Column
  private String courseCode;
  @ManyToOne
  @JoinColumn(name = "weightGradeId")
  private WeightGrade weightGrade;
  @OneToMany(mappedBy = "gradeClassCourse", fetch = FetchType.LAZY)
  private List<StudentGrade> studentGrades;
}
