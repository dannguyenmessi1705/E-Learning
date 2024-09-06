package com.didan.elearning.users.service;

import com.didan.elearning.users.dto.response.ActivityResponseDto;
import java.util.List;

public interface IActivityService {
    ActivityResponseDto createActivity(String userId , String activityContent);
    List<ActivityResponseDto> getActivities(String userId);

}
