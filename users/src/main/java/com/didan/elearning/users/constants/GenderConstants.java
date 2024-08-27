package com.didan.elearning.users.constants;

import lombok.Getter;

@Getter
public enum GenderConstants {
  MALE("MALE"),
  FEMAIL("FEMAIL"),
  UNDERTERMINED("UNDERTERMINED");

  private final String gender;
  GenderConstants(String gender) {
    this.gender = gender;
  }
}
