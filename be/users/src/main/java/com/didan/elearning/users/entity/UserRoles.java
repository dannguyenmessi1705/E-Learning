package com.didan.elearning.users.entity;

import com.didan.elearning.users.entity.key.UserRoleId;
import jakarta.persistence.EmbeddedId;
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
public class UserRoles extends SuperClass {
  @EmbeddedId
  private UserRoleId userRoleId;
  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;
  @ManyToOne
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;
}
