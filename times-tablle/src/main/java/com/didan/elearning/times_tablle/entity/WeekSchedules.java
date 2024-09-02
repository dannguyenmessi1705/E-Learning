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
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class WeekSchedules extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String weekScheduleId;
  @Column
  private Integer weekNumber;
  @Column
  private Date startWeek;
  @Column
  private Date endWeek;
  @ManyToOne
  @JoinColumn(name = "timesTableId")
  private TimesTable timesTable;
  @OneToMany(mappedBy = "weekSchedules", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<DateSchedules> dateSchedules;
}
