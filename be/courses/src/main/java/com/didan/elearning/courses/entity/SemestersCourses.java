package com.didan.elearning.courses.entity;

import com.didan.elearning.courses.entity.key.SemestersCoursesId;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
public class SemestersCourses extends SuperClass {
  @EmbeddedId
  private SemestersCoursesId semestersCoursesId;
  @ManyToOne
  @JoinColumn(name = "semester_id", insertable = false, updatable = false)
  private Semester semester;
  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;
}
