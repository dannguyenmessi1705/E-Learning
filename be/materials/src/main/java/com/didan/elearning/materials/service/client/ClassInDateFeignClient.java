package com.didan.elearning.materials.service.client;

import com.didan.elearning.materials.dto.response.ClassInDateResponseDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "times-table")
public interface ClassInDateFeignClient {
  @GetMapping("times-table/v1/class-date/get/{classInDateId}")
  ResponseEntity<GeneralResponse<ClassInDateResponseDto>> getClassInDateById(
      @NotBlank(message = "Class in date ID is required") @PathVariable("classInDateId") String classInDateId);
}
