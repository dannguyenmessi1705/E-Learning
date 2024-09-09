package com.didan.elearning.courses.entity;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class CourseClasses extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String classId;
  @Column(unique = true)
  private String classCode;
  @Column
  private String instructorId;
  @Column
  private String assistantId;
  @Column
  private String className;
  @Column
  private Integer capacity;
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;
  @OneToMany(mappedBy = "courseClasses", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<ClassStudents> classStudents;
}
