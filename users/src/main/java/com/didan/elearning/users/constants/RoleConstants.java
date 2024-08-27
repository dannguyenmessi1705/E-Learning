package com.didan.elearning.users.constants;

import lombok.Getter;

@Getter
public enum RoleConstants {
  ADMIN("ADMIN"),
  INSTRUCTOR("INSTRUCTOR"),
  ASSISTANT("ASSISTANT"),
  STUDENT("STUDENT"),
  GUEST("GUEST");
  private String role;
  RoleConstants(String role) {
    this.role = role;
  }
}
