package com.didan.elearning.times_tablle.entity;

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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class DateSchedules extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String dateScheduleId;
  @Column
  private String date; // DateConstant
  @Column
  private Boolean isHoliday;
  @ManyToOne
  @JoinColumn(name = "weekScheduleId")
  private WeekSchedules weekSchedules;
  @OneToMany(mappedBy = "dateSchedules", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private List<ClassInDate> classInDates;
}
