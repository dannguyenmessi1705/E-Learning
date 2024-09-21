package com.didan.elearning.materials.service.client;

import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.dto.response.UpdateUserDetailResponseDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UserFeignClient {
  @GetMapping("users/v1/get/{id}")
  ResponseEntity<GeneralResponse<UpdateUserDetailResponseDto>> getUserDetails(
      @NotBlank(message = "User ID is required")
      @PathVariable("id") String userId);
}
