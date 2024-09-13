package com.didan.elearning.courses.service.impl;

import com.didan.elearning.courses.constants.MessageConstants;
import com.didan.elearning.courses.dto.request.SemesterRequestDto;
import com.didan.elearning.courses.dto.response.SemesterResponseDto;
import com.didan.elearning.courses.entity.Semester;
import com.didan.elearning.courses.exception.ResourceAlreadyExistException;
import com.didan.elearning.courses.repository.SemesterRepository;
import com.didan.elearning.courses.service.ISemesterService;
import com.didan.elearning.courses.utils.MapperUtils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SemesterServiceImpl implements ISemesterService {
  private final SemesterRepository semesterRepository;

  @Override
  public SemesterResponseDto createSemester(SemesterRequestDto semesterRequestDto) {
    String semesterCode = semesterRequestDto.getSemesterCode();
    semesterRepository.findSemesterBySemesterCodeIgnoreCase(semesterCode)
        .ifPresent(semester -> {
          log.error("Semester with code {} already exists", semesterCode);
          throw  new ResourceAlreadyExistException(String.format(MessageConstants.SEMESTER_ALREADY_EXISTS, semesterCode));
        });
    String semesterName = "Semester " + semesterCode.split("-")[1] + " in " + semesterCode.split("-")[0];
    LocalDate startDate = LocalDate.parse(semesterRequestDto.getStartDate()).with(DayOfWeek.MONDAY); // Start from Monday of the week of date value
    LocalDate endDate = LocalDate.parse(semesterRequestDto.getEndDate()).with(DayOfWeek.SUNDAY); // End on Sunday of the week of date value
    Semester semester = Semester.builder()
        .semesterCode(semesterCode)
        .name(semesterName)
        .startDate(startDate)
        .endDate(endDate)
        .build();
    semesterRepository.save(semester);
    SemesterResponseDto semesterResponseDto = MapperUtils.map(semester, SemesterResponseDto.class);
    log.info("Semester created successfully");
    return semesterResponseDto;
  }

  @Override
  public List<SemesterResponseDto> getAllSemesters() {
    List<Semester> semesters = semesterRepository.findAll(
        Sort.by(Sort.Order.desc("endDate"))
    );
    if (semesters.isEmpty()) {
      log.info("No semesters found");
      return List.of();
    }
    log.info("Returning all semesters");
    return MapperUtils.mapList(semesters, SemesterResponseDto.class);
  }
}
