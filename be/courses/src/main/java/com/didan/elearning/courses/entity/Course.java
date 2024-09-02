package com.didan.elearning.courses.entity;

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
public class Course extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String courseId;
  @Column(unique = true)
  private String courseCode;
  @Column
  private String title;
  @Column
  private String description;
  @ManyToOne
  @JoinColumn(name = "semester_id")
  private Semester semester;
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private List<CourseClasses> courseClasses;
}
