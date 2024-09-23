package com.didan.elearning.courses.service.client;

import com.didan.elearning.courses.dto.response.GeneralResponse;
import com.didan.elearning.courses.dto.response.RoleResponseDto;
import com.didan.elearning.courses.dto.response.UpdateUserDetailResponseDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UsersFeignClient {
  @GetMapping("users/v1/get/student/{studentCode}")
  ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getStudentByStudentCode(
      @NotBlank(message = "Student code is required")
      @PathVariable("studentCode") String studentCode);

  @GetMapping("users/v1/get/role/{id}")
  ResponseEntity<GeneralResponse<RoleResponseDto>> getRoleForUser(
      @NotBlank(message = "User ID is required")
      @PathVariable("id") String userId);

  @GetMapping("/get/{id}")
  ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getUserDetails(
      @NotBlank(message = "User ID is required")
      @PathVariable("id") String userId);
}
