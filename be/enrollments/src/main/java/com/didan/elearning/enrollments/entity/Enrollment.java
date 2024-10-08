package com.didan.elearning.enrollments.entity;

import com.didan.elearning.enrollments.constant.EnrollmentStatusConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
  private String studentCode;
  @Column
  private String status; // EnrollmentStatusConstant
  @ManyToOne
  @JoinColumn(name = "classDetailsId")
  private ClassRegistrationDetails classRegistrationDetails;
  @PrePersist
  public void prePersist() {
    this.status = EnrollmentStatusConstant.ACTIVE;
  }
}
