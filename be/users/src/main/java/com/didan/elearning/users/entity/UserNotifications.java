package com.didan.elearning.users.entity;

import com.didan.elearning.users.constant.CheckBoolean;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class UserNotifications extends SuperClass{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String notificationId;

  @Column
  private String message;

  @Column
  private String isRead;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  private User user;

  @PrePersist
  public void setDefaultValue() {
    this.isRead = CheckBoolean.FALSE;
  }
}
