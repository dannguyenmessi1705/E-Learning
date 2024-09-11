package com.didan.elearning.enrollments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ClassRegistrationDetails extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String classDetailsId;
  @Column
  private String classCode;
  @Column
  private String semesterCode;
  @Column
  private String courseCode;
  @Column
  private Integer maxCapacity;
  @Column
  private Integer currentEnrollmentQuantity;
  @Column
  private String statusClass; // ClassStatusConstant
  @PrePersist
  public void prePersist() {
    this.currentEnrollmentQuantity = 0;
  }
}
