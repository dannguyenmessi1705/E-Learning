package com.didan.elearning.users.controller.impl;

import com.didan.elearning.users.controller.IActivityController;
import com.didan.elearning.users.dto.request.ActivityRequestDto;
import com.didan.elearning.users.dto.response.ActivityResponseDto;
import com.didan.elearning.users.dto.response.GeneralResponse;
import com.didan.elearning.users.service.IActivityService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class ActivityControllerImpl implements IActivityController {
  private final IActivityService activityService;
  @Override
  public ResponseEntity<GeneralResponse<ActivityResponseDto>> createActivity(String userId,
      ActivityRequestDto activityRequestDto) {
    log.info("Creating activity for user: {}", userId);
    ActivityResponseDto response = activityService.createActivity(userId, activityRequestDto.getDescription());
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "Activity created successfully", response), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<GeneralResponse<List<ActivityResponseDto>>> getActivities(String userId) {
    log.info("Getting activities for user: {}", userId);
    List<ActivityResponseDto> response = activityService.getActivities(userId);
    return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "Activities retrieved successfully", response), HttpStatus.OK);
  }
}
