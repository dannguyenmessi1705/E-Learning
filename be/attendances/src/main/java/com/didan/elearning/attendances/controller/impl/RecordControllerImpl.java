package com.didan.elearning.attendances.controller.impl;

import com.didan.elearning.attendances.controller.IRecordController;
import com.didan.elearning.attendances.dto.request.RecordUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.GeneralResponse;
import com.didan.elearning.attendances.dto.response.RecordResponseDto;
import com.didan.elearning.attendances.service.IRecordService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class RecordControllerImpl implements IRecordController {

  private final IRecordService recordService;

  @Override
  public ResponseEntity<GeneralResponse<RecordResponseDto>> updateRecordForAttendance(
      String recordId, RecordUpdateRequestDto recordUpdateRequestDto) {
    log.info("Updating record: {}", recordId);
    RecordResponseDto recordResponseDto = recordService.updateRecordForAttendance(recordId,
        recordUpdateRequestDto);
    return new ResponseEntity<>(
        new GeneralResponse<>(HttpStatus.OK.value(), "Record updated successfully",
            recordResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<Void>> deleteRecordForAttendance(String recordId) {
    log.info("Deleting record: {}", recordId);
    recordService.deleteRecordForAttendance(recordId);
    return new ResponseEntity<>(
        new GeneralResponse<>(HttpStatus.OK.value(), "Record deleted successfully", null),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<RecordResponseDto>> getRecordDetailsForAttendance(
      String recordId) {
    log.info("Getting record details: {}", recordId);
    RecordResponseDto recordResponseDto = recordService.getRecordDetailsForAttendance(recordId);
    return new ResponseEntity<>(
        new GeneralResponse<>(HttpStatus.OK.value(), "Record details retrieved successfully",
            recordResponseDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<RecordResponseDto>>> getAllRecordsForAttendance(
      String attendanceId) {
    log.info("Getting all records for attendance: {}", attendanceId);
    List<RecordResponseDto> recordResponseDtos = recordService.getAllRecordsForAttendance(
        attendanceId);
    return new ResponseEntity<>(
        new GeneralResponse<>(HttpStatus.OK.value(), "Records retrieved successfully",
            recordResponseDtos), HttpStatus.OK);
  }
}
