package com.didan.elearning.times_tablle.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class TimesTable extends SuperClass{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String timesTableId;
  @Column
  private String semesterCode;
  @Column
  private Date startDate;
  @Column
  private Date endDate;
  @OneToMany(mappedBy = "timesTable", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<WeekSchedules> weekSchedules;
}
