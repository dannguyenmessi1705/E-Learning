package com.didan.elearning.materials.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Slf4j
public class ExtractErrorMessageUtils {
  private ExtractErrorMessageUtils(){}
  public static String extractErrorMessage(String input) {
    String errorPart = input.substring(input.indexOf("{"), input.lastIndexOf("}") + 1);
    String errorMsg = "";
    // Chuyển đổi từ JSON sang String để lấy errorMsg
    try {
      JSONObject jsonObject = new JSONObject(errorPart);
      errorMsg = jsonObject.getString("errorMsg");
    } catch (JSONException e) {
      log.error("Error when extract error message: {}", e.getMessage());
    }
    return errorMsg;
  }
}
