package com.didan.elearning.attendances.service.impl;

import com.didan.elearning.attendances.constant.MessageConstant;
import com.didan.elearning.attendances.constant.StatusConstants;
import com.didan.elearning.attendances.dto.request.RecordUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.RecordResponseDto;
import com.didan.elearning.attendances.entity.AttendanceRecords;
import com.didan.elearning.attendances.entity.Attendances;
import com.didan.elearning.attendances.exception.FieldErrorException;
import com.didan.elearning.attendances.exception.ResourceNotFoundException;
import com.didan.elearning.attendances.repository.AttendanceRecordsRepository;
import com.didan.elearning.attendances.service.IRecordService;
import com.didan.elearning.attendances.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RecordServiceImpl implements IRecordService {
  private final AttendanceRecordsRepository recordsRepository;
  @Override
  public void createRecordForAttendance(Attendances attendances) {
    log.info("Creating records for attendance: {}, in class: {}", attendances.getAttendanceId(), attendances.getClassCode());
    List<AttendanceRecords> records = new ArrayList<>();
    /**
     * OpenFeign client to call the course service to get all students in the class course
     * ...attendances.getCourseCode());
     * forEach student in the class course, create a record for the student
     * AttendanceRecords record = new AttendanceRecords();
     * record.setStudentCode(studentCode);
     * record.setAttendance(attendances);
     * records.add(record);
     * save all records
     */
    log.info("Records created for attendance: {}", attendances.getAttendanceId());
  }

  @Override
  @Transactional
  public RecordResponseDto updateRecordForAttendance(String recordId,
      RecordUpdateRequestDto recordUpdateRequestDto) {
    AttendanceRecords record = recordsRepository.findById(recordId).orElseThrow(() -> {
      log.info("Record not found: {}", recordId);
      return new ResourceNotFoundException(String.format(MessageConstant.RECORD_NOT_FOUND, recordId));
    });
    if (recordUpdateRequestDto.getStudentCode() != null) {
      /**
       * OpenFeign call to user service to get student by studentCode to check if student exists
       * ...(recordUpdateRequestDto.getStudentCode());
       * record.setStudentCode(recordUpdateRequestDto.getStudentCode());
       */
    }
    if (recordUpdateRequestDto.getStatus().equalsIgnoreCase(StatusConstants.ABSENT)) {
      record.setStatus(StatusConstants.ABSENT);
    } else if (recordUpdateRequestDto.getStatus().equalsIgnoreCase(StatusConstants.PRESENT)) {
      record.setStatus(StatusConstants.PRESENT);
    } else if (recordUpdateRequestDto.getStatus().equalsIgnoreCase(StatusConstants.LATE)) {
      record.setStatus(StatusConstants.LATE);
    } else {
      throw new FieldErrorException("Invalid status");
    }
    recordsRepository.save(record);
    log.info("Record updated: {}", recordId);
    return MapperUtils.map(record, RecordResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteRecordForAttendance(String recordId) {
    AttendanceRecords record = recordsRepository.findById(recordId).orElseThrow(() -> {
      log.info("Record not found: {}", recordId);
      return new ResourceNotFoundException(String.format(MessageConstant.RECORD_NOT_FOUND, recordId));
    });
    recordsRepository.delete(record);
    log.info("Record deleted: {}", recordId);
  }

  @Override
  public RecordResponseDto getRecordDetailsForAttendance(String recordId) {
    AttendanceRecords record = recordsRepository.findById(recordId).orElseThrow(() -> {
      log.info("Record not found: {}", recordId);
      return new ResourceNotFoundException(String.format(MessageConstant.RECORD_NOT_FOUND, recordId));
    });
    return MapperUtils.map(record, RecordResponseDto.class);
  }

  @Override
  public List<RecordResponseDto> getAllRecordsForAttendance(String attendanceId) {
    List<AttendanceRecords> records = recordsRepository.findAttendanceRecordsByAttendances_AttendanceIdIgnoreCase(attendanceId);
    if (records.isEmpty()) {
      log.info("No records found for attendance: {}", attendanceId);
      return List.of();
    }
    return MapperUtils.mapList(records, RecordResponseDto.class);
  }
}
