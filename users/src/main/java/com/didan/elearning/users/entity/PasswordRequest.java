package com.didan.elearning.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class PasswordRequest extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String requestId;

  @Column
  private String token;

  @Column
  private LocalDateTime expiredAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  public void setDefaultValue() {
    this.expiredAt = LocalDateTime.now().plusMinutes(10);
  }
}
