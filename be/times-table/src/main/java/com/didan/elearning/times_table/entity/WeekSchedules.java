package com.didan.elearning.times_table.entity;

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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class WeekSchedules extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String weekScheduleId;
  @Column
  private Integer weekNumber;
  @Column
  private LocalDate startWeekDate;
  @Column
  private LocalDate endWeekDate;
  @Column
  private String semesterCode;
  @OneToMany(mappedBy = "weekSchedules", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<DateSchedules> dateSchedules;
}
