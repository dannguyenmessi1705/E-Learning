package com.didan.elearning.attendances.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(
    name = "RecordResponseDto",
    description = "The response body for record"
)
public class RecordResponseDto {

  @Schema(
      name = "attendanceRecordId",
      description = "The ID of the attendance record",
      example = "134543"
  )
  private String attendanceRecordId;
  @Schema(
      name = "studentCode",
      description = "The code of the student",
      example = "D2020VT001"
  )
  private String studentCode;
  @Schema(
      name = "attendAt",
      description = "The time when the student attends",
      example = "2021-09-01T08:00:00"
  )
  private String attendAt;
  @Schema(
      name = "status",
      description = "The status of the student",
      example = "PRESENT"
  )
  private String status;
}
