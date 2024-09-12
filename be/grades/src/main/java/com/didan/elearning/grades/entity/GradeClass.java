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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class GradeClass extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String gradeClassId;
  @Column
  private String classCode;
  @ManyToOne
  @JoinColumn(name = "weightGradeId")
  private WeightGrade weightGrade;
  @OneToMany(mappedBy = "gradeClass", fetch = FetchType.LAZY)
  private List<StudentGrade> studentGrades;
}
