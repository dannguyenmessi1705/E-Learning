package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.dto.response.ActivityResponseDto;
import com.didan.elearning.users.entity.ActivityLogs;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.utils.MapperUtils;
import com.didan.elearning.users.repository.ActivityLogsRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.service.IActivityService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ActivityServiceImpl implements IActivityService {
  private final UserRepository userRepository;
  private final ActivityLogsRepository activityLogsRepository;
  @Override
  public ActivityResponseDto createActivity(String userId, String activityContent) {
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info("User not found - {}", userId);
      return new RuntimeException(MessageConstant.USER_NOT_FOUND + " - " + userId);
    });
    ActivityLogs activity = new ActivityLogs();
    activity.setUser(user);
    activity.setDescription(activityContent);
    activityLogsRepository.save(activity);
    ActivityResponseDto response = MapperUtils.map(activity, ActivityResponseDto.class);
    response.setUserId(userId);
    log.info(MessageConstant.ACTIVITY_CREATED_SUCCESSFULLY);
    return response;
  }

  @Override
  public List<ActivityResponseDto> getActivities(String userId) {
    userRepository.findById(userId).orElseThrow(() -> {
      log.info("User not found - {}", userId);
      return new RuntimeException(MessageConstant.USER_NOT_FOUND + " - " + userId);
    });
    List<ActivityLogs> activities = activityLogsRepository.findActivityLogsByUser_UserIdOrderByCreatedAt(userId);
    List<ActivityResponseDto> response = MapperUtils.mapList(activities, ActivityResponseDto.class);
    response.forEach(activity -> activity.setUserId(userId));
    log.info(MessageConstant.ACTIVITIES_RETRIEVED_SUCCESSFULLY + "with userId: {}", userId);
    return response;
  }
}
