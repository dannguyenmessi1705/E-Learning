package com.didan.elearning.enrollments.service;

import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsCreateRequestDto;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsFilterRequest;
import com.didan.elearning.enrollments.dto.request.ClassRegistrationDetailsUpdateRequestDto;
import com.didan.elearning.enrollments.dto.response.ClassRegistrationDetailsResponseDto;
import java.util.List;

public interface IClassRegistrationDetailsService {

  ClassRegistrationDetailsResponseDto createClassRegistrationDetails(
      ClassRegistrationDetailsCreateRequestDto classRegistrationDetailsCreateRequestDto);

  ClassRegistrationDetailsResponseDto updateClassRegistrationDetails(
      String classRegistrationId,
      ClassRegistrationDetailsUpdateRequestDto classRegistrationDetailsUpdateRequestDto);

  ClassRegistrationDetailsResponseDto getClassRegistrationDetails(
      String classRegistrationId);

  void deleteClassRegistrationDetails(String classRegistrationId);
  List<ClassRegistrationDetailsResponseDto> filterClassRegistrationDetails(
      ClassRegistrationDetailsFilterRequest classRegistrationDetailsFilterRequest);
}
