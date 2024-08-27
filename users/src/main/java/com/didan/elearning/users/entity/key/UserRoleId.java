package com.didan.elearning.users.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
  @Column(name = "id")
  private String userId;
  @Column(name = "id")
  private String roleId;
}
