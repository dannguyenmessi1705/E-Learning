package com.didan.elearning.courses.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(
    name = "GeneralResponse",
    description = "The general response body"
)
public class GeneralResponse<T> {
  @Schema(
      name = "statusCode",
      description = "The status code of the response",
      example = "200"
  )
  private Integer statusCode;
  @Schema(
      name = "message",
      description = "The message of the response",
      example = "Success"
  )
  private String message;
  @Schema(
      name = "data",
      description = "The data of the response"
  )
  private T data;
}
