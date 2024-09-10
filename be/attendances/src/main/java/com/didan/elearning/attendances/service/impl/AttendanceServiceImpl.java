package com.didan.elearning.attendances.service.impl;

import com.didan.elearning.attendances.constant.MessageConstant;
import com.didan.elearning.attendances.dto.request.AttendanceRequestDto;
import com.didan.elearning.attendances.dto.request.AttendanceUpdateRequestDto;
import com.didan.elearning.attendances.dto.response.AttendanceResponseDto;
import com.didan.elearning.attendances.entity.Attendances;
import com.didan.elearning.attendances.exception.ResourceNotFoundException;
import com.didan.elearning.attendances.repository.AttendancesRepository;
import com.didan.elearning.attendances.service.IAttendanceService;
import com.didan.elearning.attendances.service.IRecordService;
import com.didan.elearning.attendances.utils.MapperUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements IAttendanceService {

  private final AttendancesRepository attendancesRepository;
  private final IRecordService recordService;

  @Override
  public AttendanceResponseDto createAttendanceForClass(AttendanceRequestDto attendanceRequestDto) {
    /**
     * OpenFeign call to get Course by courseCode to check if course exists
     * ...(attendanceRequestDto.getCourseCode());
     * OpenFeign call to get Class by classCode to check if class exists
     * ...(attendanceRequestDto.getClassCode());
     * OpenFeign call to get Semester by semesterCode to check if semester exists
     * ...(attendanceRequestDto.getSemesterCode());
     */
    Attendances newAttendance = MapperUtils.map(attendanceRequestDto, Attendances.class);
    newAttendance.setAttendanceTime(LocalDateTime.parse(attendanceRequestDto.getAttendanceTime()));
    attendancesRepository.save(newAttendance);
    log.info(String.format(MessageConstant.ATTENDANCE_CREATED, newAttendance.getClassCode(),
        newAttendance.getAttendanceTime()));
    recordService.createRecordForAttendance(newAttendance);
    return MapperUtils.map(newAttendance, AttendanceResponseDto.class);
  }

  @Override
  public AttendanceResponseDto getAttendanceDetailsForClass(String attendanceId) {
    Attendances attendances = attendancesRepository.findById(attendanceId)
        .orElseThrow(() -> {
          log.info(String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND, attendanceId));
          return new ResourceNotFoundException(
              String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND,
                  attendanceId));
        });
    return MapperUtils.map(attendances, AttendanceResponseDto.class);
  }

  @Override
  public List<AttendanceResponseDto> getAllAttendancesForClass(String classCode) {
    /**
     * OpenFeign call to get Class by classCode to check if class exists
     * ...(classCode);
     * if class does not exist, throw ResourceNotFoundException
     */
    List<Attendances> attendances = attendancesRepository.findAttendancesByClassCodeIgnoreCase(
        classCode);
    if (attendances.isEmpty()) {
      log.info(String.format(MessageConstant.ATTENDANCE_NOT_FOUND, classCode));
      return List.of();
    }
    return MapperUtils.mapList(attendances, AttendanceResponseDto.class);
  }

  @Override
  @Transactional
  public AttendanceResponseDto updateAttendanceForClass(String attendanceId,
      AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
    Attendances attendances = attendancesRepository.findById(attendanceId)
        .orElseThrow(() -> {
          log.info(String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND, attendanceId));
          return new ResourceNotFoundException(
              String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND,
                  attendanceId));
        });
    /**
     * OpenFeign call to get Course by courseCode to check if course exists
     * if (StringUtils.hasText(attendanceRequestDto.getCourseCode())) {
     * ...(attendanceRequestDto.getCourseCode());
     * }
     * OpenFeign call to get Class by classCode to check if class exists
     * if (StringUtils.hasText(attendanceRequestDto.getClassCode())) {
     * ...(attendanceRequestDto.getClassCode());
     * }
     * OpenFeign call to get Semester by semesterCode to check if semester exists
     * if (StringUtils.hasText(attendanceRequestDto.getSemesterCode())) {
     * ...(attendanceRequestDto.getSemesterCode());
     * }
     */
    if (attendanceUpdateRequestDto.getAttendanceTime() != null) {
      attendances.setAttendanceTime(LocalDateTime.parse(attendanceUpdateRequestDto.getAttendanceTime()));
    }
    attendancesRepository.save(attendances);
    log.info(String.format(MessageConstant.ATTENDANCE_UPDATED, attendances.getAttendanceId(),
        attendances.getClassCode()));
    return MapperUtils.map(attendances, AttendanceResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteAttendanceForClass(String attendanceId) {
    Attendances attendances = attendancesRepository.findById(attendanceId).orElseThrow(() -> {
      log.info(String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND, attendanceId));
      return new ResourceNotFoundException(
          String.format(MessageConstant.ATTENDANCE_DETAILS_NOT_FOUND,
              attendanceId));
    });
    attendancesRepository.deleteById(attendanceId);
    log.info(String.format(MessageConstant.ATTENDANCE_DELETED, attendanceId, attendances.getClassCode()));
  }
}
