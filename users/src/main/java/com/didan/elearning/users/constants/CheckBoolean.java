package com.didan.elearning.users.constants;

import lombok.Getter;

@Getter
public enum CheckBoolean {
  TRUE("TRUE"),
  FALSE("FALSE");

  private final String checkBoolean;
  CheckBoolean(String checkBoolean) {
    this.checkBoolean = checkBoolean;
  }
}
