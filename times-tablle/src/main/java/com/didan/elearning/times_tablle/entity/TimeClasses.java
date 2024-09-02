package com.didan.elearning.times_tablle.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class TimeClasses extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String timeClassId;
  @Column
  private String classCode;
  @Column
  private String className;
  @Column
  private String courseCode;
  @Column
  private String instructorId;
  @OneToMany(mappedBy = "timeClasses", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private List<ClassInDate> classInDates;
}
