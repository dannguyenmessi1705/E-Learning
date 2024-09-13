package com.didan.elearning.times_table.service;

import com.didan.elearning.times_table.dto.request.ClassInDateRequestDto;
import com.didan.elearning.times_table.dto.response.ClassInDateResponseDto;

public interface IClassInDateService {
  ClassInDateResponseDto createClassInDate(ClassInDateRequestDto classInDateRequestDto);
}
