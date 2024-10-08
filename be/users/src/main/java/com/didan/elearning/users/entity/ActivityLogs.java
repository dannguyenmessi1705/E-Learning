package com.didan.elearning.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ActivityLogs extends SuperClass{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String activityLogId;

  @Column
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
