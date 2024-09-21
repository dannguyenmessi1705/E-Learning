package com.didan.elearning.materials.service.client;

import com.didan.elearning.materials.dto.response.ClassResponseDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courses")
public interface CoursesFeignClient {
  @GetMapping("courses/v1/classes/{classCode}")
  ResponseEntity<GeneralResponse<ClassResponseDto>> getClassByCode(@NotEmpty(message = "Class code is required") @PathVariable("classCode") String classCode);
}
