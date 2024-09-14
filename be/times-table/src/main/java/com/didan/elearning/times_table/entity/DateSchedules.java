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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class DateSchedules extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String dateScheduleId;
  @Column(name = "date_schedule")
  private LocalDate date;
  @Column(name = "day_schedule")
  private String day;  // DateConstant
  @Column
  private String isDayOff;
  @ManyToOne
  @JoinColumn(name = "weekScheduleId")
  private WeekSchedules weekSchedules;
  @OneToMany(mappedBy = "dateSchedules", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private List<ClassInDate> classInDates;
}
