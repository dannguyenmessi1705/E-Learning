package com.didan.elearning.grades.service.impl;

import com.didan.elearning.grades.constant.MessageConstant;
import com.didan.elearning.grades.constant.TypeGradeConstant;
import com.didan.elearning.grades.dto.request.StudentGradeRequestDto;
import com.didan.elearning.grades.dto.response.StudentGradeResponseDto;
import com.didan.elearning.grades.entity.GradeClass;
import com.didan.elearning.grades.entity.StudentGrade;
import com.didan.elearning.grades.exception.FieldErrorException;
import com.didan.elearning.grades.repository.GradeClassRepository;
import com.didan.elearning.grades.repository.StudentGradeRepository;
import com.didan.elearning.grades.repository.WeightGradeRepository;
import com.didan.elearning.grades.service.IStudentGradeService;
import com.didan.elearning.grades.utils.MapperUtils;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class StudentGradeServiceImpl implements IStudentGradeService {
  private final StudentGradeRepository studentGradeRepository;
  private final GradeClassRepository gradeClassRepository;
  private final WeightGradeRepository weightGradeRepository;

  @Override
  public StudentGradeResponseDto createStudentGrade(StudentGradeRequestDto studentGradeRequestDto) {
    validateInput(studentGradeRequestDto);
    GradeClass gradeClass = gradeClassRepository.findById(studentGradeRequestDto.getGradeClassId())
        .orElseThrow(() -> {
          log.error("Grade class with id {} not found", studentGradeRequestDto.getGradeClassId());
          return new FieldErrorException(String.format(MessageConstant.GRADE_CLASS_NOT_FOUND_ID, studentGradeRequestDto.getGradeClassId()));
        });

    /**
     * OpenFeign call to user service to get student information
     * if student not found throw exception
     * if student found next step
     * */

    StudentGrade studentGrade = MapperUtils.map(studentGradeRequestDto, StudentGrade.class);
    studentGrade.setGradeClass(gradeClass);
    double totalWeight = calculateTotalScore(studentGrade);
    studentGrade.setTotalScore(totalWeight);
    studentGrade.setTypeGrade(assignGradeType(totalWeight));
    studentGradeRepository.save(studentGrade);
    log.info("Student grade created successfully");
    return MapperUtils.map(studentGrade, StudentGradeResponseDto.class);
  }

  @Override
  public StudentGradeResponseDto updateStudentGrade(String studentGradeId,
      StudentGradeRequestDto studentGradeRequestDto) {
    StudentGrade studentGrade = studentGradeRepository.findById(studentGradeId)
        .orElseThrow(() -> {
          log.error("Student grade with id {} not found", studentGradeId);
          return new FieldErrorException(String.format(MessageConstant.STUDENT_GRADE_NOT_FOUND_ID, studentGradeId));
        });
    if (StringUtils.hasText(studentGradeRequestDto.getStudentCode())) {
      /**
       * OpenFeign call to user service to get student information
       * if student not found throw exception
       * if student found next step
       */
      studentGrade.setStudentCode(studentGradeRequestDto.getStudentCode());
    }
    if (StringUtils.hasText(studentGradeRequestDto.getGradeClassId())) {
      GradeClass gradeClass = gradeClassRepository.findById(studentGradeRequestDto.getGradeClassId())
          .orElseThrow(() -> {
            log.error("Grade class with id {} not found", studentGradeRequestDto.getGradeClassId());
            return new FieldErrorException(String.format(MessageConstant.GRADE_CLASS_NOT_FOUND_ID, studentGradeRequestDto.getGradeClassId()));
          });
      studentGrade.setGradeClass(gradeClass);
    }
    if (StringUtils.hasText(studentGradeRequestDto.getAttendanceScore().toString())) {
      studentGrade.setAttendanceScore(studentGradeRequestDto.getAttendanceScore());
    }
    if (StringUtils.hasText(studentGradeRequestDto.getAssignmentScore().toString())) {
      studentGrade.setAssignmentScore(studentGradeRequestDto.getAssignmentScore());
    }
    if (StringUtils.hasText(studentGradeRequestDto.getMidtermScore().toString())) {
      studentGrade.setMidtermScore(studentGradeRequestDto.getMidtermScore());
    }
    if (StringUtils.hasText(studentGradeRequestDto.getPracticeScore().toString())) {
      studentGrade.setPracticeScore(studentGradeRequestDto.getPracticeScore());
    }
    if (StringUtils.hasText(studentGradeRequestDto.getFinalScore().toString())) {
      studentGrade.setFinalScore(studentGradeRequestDto.getFinalScore());
    }
    double totalWeight = calculateTotalScore(studentGrade);
    studentGrade.setTotalScore(totalWeight);
    studentGrade.setTypeGrade(assignGradeType(totalWeight));
    studentGradeRepository.save(studentGrade);
    log.info("Student grade updated successfully");
    return MapperUtils.map(studentGrade, StudentGradeResponseDto.class);
  }

  @Override
  public List<StudentGradeResponseDto> getStudentGradeByStudentCode(String studentCode) {
    /**
     * OpenFeign call to user service to get student information
     * if student not found throw exception
     * if student found next step
     */
    List<StudentGrade> studentGrades = studentGradeRepository.findStudentGradeByStudentCodeIgnoreCase(studentCode);
    if (studentGrades.isEmpty()) {
      log.info("Student grade with student code {} not found", studentCode);
      return List.of();
    }
    log.info("Student grade with student code {} found", studentCode);
    return MapperUtils.mapList(studentGrades, StudentGradeResponseDto.class);
  }

  @Override
  public List<StudentGradeResponseDto> getStudentGradeByClassCode(String classCode) {
    /**
     * OpenFeign call to course service to get class information
     * if class not found throw exception
     * if class found next step
     */
    List<StudentGrade> studentGrades = studentGradeRepository.findStudentGradeByGradeClass_ClassCodeIgnoreCase(classCode);
    if (studentGrades.isEmpty()) {
      log.info("Student grade with class code {} not found", classCode);
      return List.of();
    }
    log.info("Student grade with class code {} found", classCode);
    return MapperUtils.mapList(studentGrades, StudentGradeResponseDto.class);
  }

  @Override
  public StudentGradeResponseDto getGradeByStudentCodeAndClassCode(String classCode,
      String studentCode) {
    /**
     * OpenFeign call to course service to get class information
     * if class not found throw exception
     * if class found next step
     */

    /**
     * OpenFeign call to user service to get student information
     * if student not found throw exception
     * if student found next step
     */
    StudentGrade studentGrade = studentGradeRepository.findStudentGradeByStudentCodeAndGradeClass_ClassCodeIgnoreCase(studentCode, classCode)
        .orElseThrow(() -> {
          log.error("Student grade with student code {} and class code {} not found", studentCode, classCode);
          return new FieldErrorException(String.format(MessageConstant.STUDENT_GRADE_NOT_FOUND_STUDENT_CODE_CLASS_CODE, studentCode, classCode));
        });
    log.info("Student grade with student code {} and class code {} found", studentCode, classCode);
    return MapperUtils.map(studentGrade, StudentGradeResponseDto.class);
  }

  @Override
  public void deleteStudentGrade(String studentGradeId) {
    StudentGrade studentGrade = studentGradeRepository.findById(studentGradeId)
        .orElseThrow(() -> {
          log.error("Student grade with id {} not found", studentGradeId);
          return new FieldErrorException(String.format(MessageConstant.STUDENT_GRADE_NOT_FOUND_ID, studentGradeId));
        });
    studentGradeRepository.delete(studentGrade);
    log.info("Student grade with id {} deleted successfully", studentGradeId);
  }

  void validateInput(StudentGradeRequestDto studentGradeRequestDto) {
    if (!StringUtils.hasText(studentGradeRequestDto.getStudentCode())) {
      log.error("Student code is required");
      throw new FieldErrorException("Student code is required");
    }
    if (!StringUtils.hasText(studentGradeRequestDto.getGradeClassId())) {
      log.error("Grade class id is required");
      throw new FieldErrorException("Grade class id is required");
    }
  }

  String assignGradeType(Double totalScore) {
    if (totalScore < Double.parseDouble("4.0")) {
      return TypeGradeConstant.F;
    } else if (totalScore >= Double.parseDouble("4.0") && totalScore < Double.parseDouble("5.0")) {
      return TypeGradeConstant.D_PLUS;
    } else if (totalScore >= Double.parseDouble("5.0") && totalScore < Double.parseDouble("6.0")) {
      return TypeGradeConstant.C;
    } else if (totalScore >= Double.parseDouble("6.0") && totalScore < Double.parseDouble("7.0")) {
      return TypeGradeConstant.C_PLUS;
    } else if (totalScore >= Double.parseDouble("7.0") && totalScore < Double.parseDouble("8.0")) {
      return TypeGradeConstant.B;
    } else if (totalScore >= Double.parseDouble("8.0") && totalScore < Double.parseDouble("8.5")) {
      return TypeGradeConstant.B_PLUS;
    } else if (totalScore >= Double.parseDouble("8.5") && totalScore < Double.parseDouble("9.0")) {
      return TypeGradeConstant.A;
    } else {
      return TypeGradeConstant.A_PLUS;
    }
  }

  Double calculateTotalScore(StudentGrade studentGrade) {
    return studentGrade.getAttendanceScore() * studentGrade.getGradeClass().getWeightGrade()
        .getAttendanceWeight()
        + studentGrade.getAssignmentScore() * studentGrade.getGradeClass().getWeightGrade()
        .getAssignmentWeight()
        + studentGrade.getMidtermScore() * studentGrade.getGradeClass().getWeightGrade()
        .getMidtermWeight()
        + studentGrade.getPracticeScore() * studentGrade.getGradeClass().getWeightGrade()
        .getPracticeWeight()
        + studentGrade.getFinalScore() * studentGrade.getGradeClass().getWeightGrade()
        .getFinalWeight();
  }
}
