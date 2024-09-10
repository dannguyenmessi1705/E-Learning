package com.didan.elearning.courses.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class ClassStudents extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column
  private String studentCode;
  @ManyToOne
  @JoinColumn(name = "class_id")
  private CourseClasses courseClasses;
}
