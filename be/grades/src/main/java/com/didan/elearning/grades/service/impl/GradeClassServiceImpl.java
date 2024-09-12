package com.didan.elearning.grades.service.impl;

import com.didan.elearning.grades.constant.MessageConstant;
import com.didan.elearning.grades.dto.request.GradeClassRequestDto;
import com.didan.elearning.grades.dto.response.GradeClassResponseDto;
import com.didan.elearning.grades.dto.response.WeightGradeResponseDto;
import com.didan.elearning.grades.entity.GradeClass;
import com.didan.elearning.grades.entity.WeightGrade;
import com.didan.elearning.grades.exception.FieldErrorException;
import com.didan.elearning.grades.exception.ResourceNotFoundException;
import com.didan.elearning.grades.repository.GradeClassRepository;
import com.didan.elearning.grades.repository.WeightGradeRepository;
import com.didan.elearning.grades.service.IGradeClassService;
import com.didan.elearning.grades.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class GradeClassServiceImpl implements IGradeClassService {
  private final GradeClassRepository gradeClassRepository;
  private final WeightGradeRepository weightGradeRepository;

  @Override
  public GradeClassResponseDto createGradeClass(GradeClassRequestDto gradeClassRequestDto) {
    if (!StringUtils.hasText(gradeClassRequestDto.getClassCode())) {
      log.error("Class code is required");
      throw new FieldErrorException("Class code is required");
    }
    if (!StringUtils.hasText(gradeClassRequestDto.getWeightGradeId())) {
      log.error("Weight grade id is required");
      throw new FieldErrorException("Weight grade id is required");
    }
    if (gradeClassRepository.findGradeClassByClassCodeIgnoreCase(gradeClassRequestDto.getClassCode()).isPresent()) {
      log.error("Grade class with class code {} already exists", gradeClassRequestDto.getClassCode());
      throw new FieldErrorException(
          String.format(MessageConstant.GRADE_CLASS_ALREADY_EXISTS_CLASS, gradeClassRequestDto.getClassCode()));
    }
    WeightGrade weightGrade = validateWeightGrade(gradeClassRequestDto.getWeightGradeId());
    /**
     * OpenFeign call to get course service to check if class code is valid
     * if classCode does not exist in course service, throw FieldErrorException
     * if exist, check weightGrade,getClassCode() === courseCode of class
     * if not equal, throw FieldErrorException
     * else create gradeClass
     */
    GradeClass newGradeClass = GradeClass.builder()
        .classCode(gradeClassRequestDto.getClassCode())
        .weightGrade(weightGrade)
        .build();
    gradeClassRepository.save(newGradeClass);
    GradeClassResponseDto responseDto = MapperUtils.map(newGradeClass, GradeClassResponseDto.class);
    responseDto.setWeightGrade(MapperUtils.map(weightGrade, WeightGradeResponseDto.class));
    log.info("Grade class with class code {} created successfully", gradeClassRequestDto.getClassCode());
    return responseDto;
  }

  @Override
  @Transactional
  public GradeClassResponseDto updateGradeClass(String gradeClassId,
      GradeClassRequestDto gradeClassRequestDto) {
    GradeClass gradeClass = gradeClassRepository.findById(gradeClassId)
        .orElseThrow(() -> {
          log.error("Grade class not found");
          return new ResourceNotFoundException("Grade class not found");
        });
    if (StringUtils.hasText(gradeClassRequestDto.getWeightGradeId())) {
      WeightGrade weightGrade = validateWeightGrade(gradeClassRequestDto.getWeightGradeId());
      gradeClass.setWeightGrade(weightGrade);
    }
    if (StringUtils.hasText(gradeClassRequestDto.getClassCode())) {
      /**
       * OpenFeign call to get course service to check if class code is valid
       * if classCode does not exist in course service, throw FieldErrorException
       * if exist, check gradeClass.getWeightGrade().getCourseCode() === courseCode of class
       * if not equal, throw FieldErrorException
       * else update gradeClass
       */
      gradeClass.setClassCode(gradeClassRequestDto.getClassCode());
    }
    gradeClassRepository.save(gradeClass);
    GradeClassResponseDto responseDto = MapperUtils.map(gradeClass, GradeClassResponseDto.class);
    responseDto.setWeightGrade(MapperUtils.map(gradeClass.getWeightGrade(), WeightGradeResponseDto.class));
    log.info("Grade class with id {} updated successfully", gradeClassId);
    return responseDto;
  }

  @Override
  public GradeClassResponseDto getGradeByClassCode(String classCode) {
    GradeClass gradeClass = gradeClassRepository.findGradeClassByClassCodeIgnoreCase(classCode)
        .orElseThrow(() -> {
          log.error("Grade class not found");
          return new ResourceNotFoundException("Grade class not found");
        });
    GradeClassResponseDto responseDto = MapperUtils.map(gradeClass, GradeClassResponseDto.class);
    responseDto.setWeightGrade(MapperUtils.map(gradeClass.getWeightGrade(), WeightGradeResponseDto.class));
    log.info("Grade class with class code {} found", classCode);
    return responseDto;
  }


  @Override
  @Transactional
  public void deleteGradeClass(String gradeClassId) {
    GradeClass gradeClass = gradeClassRepository.findById(gradeClassId)
        .orElseThrow(() -> {
          log.error("Grade class not found");
          return new ResourceNotFoundException("Grade class not found");
        });
    gradeClassRepository.delete(gradeClass);
    log.info("Grade class with id {} deleted successfully", gradeClassId);
  }

  WeightGrade validateWeightGrade(String weightGradeId) {
    WeightGrade weightGrade = weightGradeRepository.findById(weightGradeId)
        .orElseThrow(() -> {
          log.error("Weight grade not found");
          return new ResourceNotFoundException(
              String.format(MessageConstant.WEIGHT_GRADE_NOT_FOUND_ID, weightGradeId));
        });
    return weightGrade;
  }
}
