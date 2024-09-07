package com.didan.elearning.courses.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class SemestersCoursesId {
  @Column(name = "semester_id")
  private String semesterId;
  @Column(name = "course_id")
  private String courseId;
}
