package com.didan.elearning.materials.constant;

public class MessageConstant {
  private MessageConstant(){}
  public static final String FILE_NOT_FOUND = "File with name %s not found";
  public static final String CLASS_NOT_FOUND = "Class with code %s not found";
  public static final String CLASS_TIME_NOT_FOUND = "Time class with ID %s not found";
  public static final String INSTRUCTION_NOT_MATCH = "Instruction not match with class, so can not update";

  public static class Status {
    private Status(){}
    public static final Integer SUCCESS = 200;
    public static final Integer NOT_FOUND = 404;
  }
}
