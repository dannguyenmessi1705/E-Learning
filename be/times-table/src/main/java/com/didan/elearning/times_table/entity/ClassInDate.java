package com.didan.elearning.times_table.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class ClassInDate extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String classInDateId;
  @Column
  private LocalTime startTime;
  @Column
  private LocalTime endTime;
  @Column
  private Integer classPeriod;

  @ManyToOne
  @JoinColumn(name = "dateScheduleId")
  private DateSchedules dateSchedules;

  @ManyToOne
  @JoinColumn(name = "timeClassId")
  private TimeClasses timeClasses;
}
