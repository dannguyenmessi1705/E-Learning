package com.didan.elearning.attendances.controller.impl;

import com.didan.elearning.attendances.controller.IAttendanceController;
import com.didan.elearning.attendances.dto.request.AttendanceRequestDto;
import com.didan.elearning.attendances.dto.request.AttendanceUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.AttendanceResponseDto;
import com.didan.elearning.attendances.dto.response.GeneralResponse;
import com.didan.elearning.attendances.service.IAttendanceService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class AttendanceControllerImpl implements IAttendanceController {
  private final IAttendanceService attendanceService;
  @Override
  public ResponseEntity<GeneralResponse<AttendanceResponseDto>> createAttendanceForClass(
      AttendanceRequestDto attendanceRequestDto) {
    log.info("Creating attendance for class {}", attendanceRequestDto.getClassCode());
    AttendanceResponseDto attendanceResponseDto = attendanceService.createAttendanceForClass(attendanceRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Attendance created successfully", attendanceResponseDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<AttendanceResponseDto>>> getAllAttendancesForClass(
      String classCode) {
    log.info("Getting all attendances for class {}", classCode);
    List<AttendanceResponseDto> attendanceResponseDtos = attendanceService.getAllAttendancesForClass(classCode);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Attendances retrieved successfully", attendanceResponseDtos), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<AttendanceResponseDto>> getAttendanceDetailsForClass(
      String attendanceId) {
    log.info("Getting attendance details for attendance {}", attendanceId);
    AttendanceResponseDto attendanceResponseDto = attendanceService.getAttendanceDetailsForClass(attendanceId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Attendance retrieved successfully", attendanceResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<AttendanceResponseDto>> updateAttendanceForClass(
      String attendanceId, AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
    log.info("Updating attendance for attendance {}", attendanceId);
    AttendanceResponseDto attendanceResponseDto = attendanceService.updateAttendanceForClass(attendanceId, attendanceUpdateRequestDto);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Attendance updated successfully", attendanceResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteAttendanceForClass(String attendanceId) {
    log.info("Deleting attendance for attendance {}", attendanceId);
    attendanceService.deleteAttendanceForClass(attendanceId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.NO_CONTENT.value(), "Attendance deleted successfully", null), HttpStatus.NO_CONTENT);
  }
}
