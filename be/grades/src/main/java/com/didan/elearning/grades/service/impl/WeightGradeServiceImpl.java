package com.didan.elearning.grades.service.impl;

import com.didan.elearning.grades.constant.MessageConstant;
import com.didan.elearning.grades.dto.request.WeightGradeRequestDto;
import com.didan.elearning.grades.dto.response.WeightGradeResponseDto;
import com.didan.elearning.grades.entity.WeightGrade;
import com.didan.elearning.grades.exception.FieldErrorException;
import com.didan.elearning.grades.exception.ResourceAlreadyExistException;
import com.didan.elearning.grades.exception.ResourceNotFoundException;
import com.didan.elearning.grades.repository.WeightGradeRepository;
import com.didan.elearning.grades.service.IWeightGradeService;
import com.didan.elearning.grades.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class WeightGradeServiceImpl implements IWeightGradeService {
  private final WeightGradeRepository weightGradeRepository;

  @Override
  public WeightGradeResponseDto createWeightGradeForCourse(
      WeightGradeRequestDto weightGradeRequestDto) {
    /**
     * OpenFeign call to course service to get course details
     * courseService.getCourseDetails(weightGradeRequestDto.getCourseCode());
     * if course is not found throw exception
     * if exist, follow the below steps
     */
    if(weightGradeRepository.findWeightGradeByCourseCodeIgnoreCase(
        weightGradeRequestDto.getCourseCode()).isPresent()) {
      log.error("Weight grade for course {} already exists", weightGradeRequestDto.getCourseCode());
      throw new ResourceAlreadyExistException(String.format(MessageConstant.WEIGHT_GRADE_ALREADY_EXISTS, weightGradeRequestDto.getCourseCode()));
    }
    Double attendanceWeight = Double.parseDouble(weightGradeRequestDto.getAttendanceWeight());
    Double assignmentWeight = Double.parseDouble(weightGradeRequestDto.getAssignmentWeight());
    Double midTermWeight = Double.parseDouble(weightGradeRequestDto.getMidtermWeight());
    Double practiceWeight = Double.parseDouble(weightGradeRequestDto.getPracticeWeight());
    Double finalWeight = Double.parseDouble(weightGradeRequestDto.getFinalWeight());

    if ((attendanceWeight + assignmentWeight + midTermWeight + practiceWeight + finalWeight) != Double.parseDouble("1.0")) {
      log.error("Sum of weights should be 1");
      throw new FieldErrorException("Sum of weights should be 1");
    }
    WeightGrade weightGrade = WeightGrade.builder()
        .courseCode(weightGradeRequestDto.getCourseCode())
        .attendanceWeight(attendanceWeight)
        .assignmentWeight(assignmentWeight)
        .midtermWeight(midTermWeight)
        .practiceWeight(practiceWeight)
        .finalWeight(finalWeight)
        .build();
    weightGradeRepository.save(weightGrade);
    log.info("Weight grade for course {} created successfully", weightGradeRequestDto.getCourseCode());
    return MapperUtils.map(weightGrade, WeightGradeResponseDto.class);
  }

  @Override
  @Transactional
  public WeightGradeResponseDto updateWeightGradeForCourse(String weightGradeId,
      WeightGradeRequestDto weightGradeRequestDto) {
    WeightGrade weightGrade = weightGradeRepository.findById(weightGradeId)
        .orElseThrow(() -> new ResourceNotFoundException("Weight grade not found"));

    if (StringUtils.hasText(weightGradeRequestDto.getAttendanceWeight())) {
          /**
           * OpenFeign call to course service to get course details
           * courseService.getCourseDetails(weightGradeRequestDto.getCourseCode());
           * if course is not found throw exception
           * if exist, follow the below steps
           */
      weightGrade.setAttendanceWeight(Double.parseDouble(weightGradeRequestDto.getAttendanceWeight()));
    }
    if (StringUtils.hasText(weightGradeRequestDto.getAttendanceWeight())) {
      weightGrade.setAttendanceWeight(Double.parseDouble(weightGradeRequestDto.getAttendanceWeight()));
    }
    if (StringUtils.hasText(weightGradeRequestDto.getAssignmentWeight())) {
      weightGrade.setAssignmentWeight(Double.parseDouble(weightGradeRequestDto.getAssignmentWeight()));
    }
    if (StringUtils.hasText(weightGradeRequestDto.getMidtermWeight())) {
      weightGrade.setMidtermWeight(Double.parseDouble(weightGradeRequestDto.getMidtermWeight()));
    }
    if (StringUtils.hasText(weightGradeRequestDto.getPracticeWeight())) {
      weightGrade.setPracticeWeight(Double.parseDouble(weightGradeRequestDto.getPracticeWeight()));
    }
    if (StringUtils.hasText(weightGradeRequestDto.getFinalWeight())) {
      weightGrade.setFinalWeight(Double.parseDouble(weightGradeRequestDto.getFinalWeight()));
    }
    if ((weightGrade.getAttendanceWeight() + weightGrade.getAssignmentWeight() + weightGrade.getMidtermWeight() + weightGrade.getPracticeWeight() + weightGrade.getFinalWeight()) != Double.parseDouble("1.0")) {
      log.error("Sum of weights should be 1");
      throw new FieldErrorException("Sum of weights should be 1");
    }
    weightGradeRepository.save(weightGrade);
    log.info("Weight grade for course {} updated successfully", weightGradeRequestDto.getCourseCode());
    return MapperUtils.map(weightGrade, WeightGradeResponseDto.class);
  }

  @Override
  public WeightGradeResponseDto getWeightGradeByCourseCode(String courseCode) {
    /**
     * OpenFeign call to course service to get course details
     * courseService.getCourseDetails(courseCode);
     * if course is not found throw exception
     * if exist, follow the below steps
     */
    WeightGrade weightGrade = weightGradeRepository.findWeightGradeByCourseCodeIgnoreCase(courseCode)
        .orElseThrow(() -> {
          log.error("Weight grade for course {} not found", courseCode);
          return new ResourceNotFoundException(String.format(MessageConstant.WEIGHT_GRADE_NOT_FOUND_COURSE, courseCode));
        });
    return MapperUtils.map(weightGrade, WeightGradeResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteWeightGrade(String weightGradeId) {
    weightGradeRepository.findById(weightGradeId)
        .orElseThrow(() -> {
          log.error("Weight grade not found");
          return new ResourceNotFoundException(String.format(MessageConstant.WEIGHT_GRADE_NOT_FOUND_ID, weightGradeId));
        });
    weightGradeRepository.deleteById(weightGradeId);
    log.info("Weight grade with id {} deleted successfully", weightGradeId);
  }
}
