package com.didan.elearning.attendances.entity;

import com.didan.elearning.attendances.constant.StatusConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class AttendanceRecords extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String attendanceRecordId;
  @Column
  private String studentCode;
  @Column
  private LocalDateTime attendAt;
  @Column
  private String status;
  @ManyToOne
  @JoinColumn(name = "attendanceId")
  private Attendances attendances;

  @PrePersist
  public void setDefaultValue() {
    this.status = StatusConstants.ABSENT;
    this.attendAt = LocalDateTime.now();
  }
  @PreUpdate
  public void updateDefaultValue() {
    this.attendAt = LocalDateTime.now();
  }
}
