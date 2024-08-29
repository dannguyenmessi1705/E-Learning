package com.didan.elearning.attendances.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Attendances extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String attendanceId;
  @Column
  private String classCode;
  @Column
  private String courseCode;
  @Column
  private String semesterCode;
  @Column
  private LocalDateTime attendanceTime;
  @OneToMany(mappedBy = "attendances")
  private List<AttendanceRecords> attendanceRecords;
}
