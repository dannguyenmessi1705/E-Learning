package com.didan.elearning.times_tablle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ClassInDate extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String classInDateId;
  @Column
  private LocalTime startTime;
  @Column
  private LocalTime endTime;
  @ManyToOne
  @JoinColumn(name = "dateScheduleId")
  private DateSchedules dateSchedules;

  @ManyToOne
  @JoinColumn(name = "timeClassId")
  private TimeClasses timeClasses;
}
