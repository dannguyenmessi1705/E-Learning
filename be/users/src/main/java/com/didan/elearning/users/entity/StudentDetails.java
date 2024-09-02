package com.didan.elearning.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class StudentDetails extends SuperClass {
  @Id
  private String userId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;
  @Column
  private String studentCode;
  @Column
  private String major;
  @Column
  private Integer startYear;
}
