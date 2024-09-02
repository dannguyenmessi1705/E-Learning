package com.didan.elearning.users.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {
  @Column(name = "user_id")
  private String userId;
  @Column(name = "role_id")
  private String roleId;
}
