package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.ActivityLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogsRepository extends JpaRepository<ActivityLogs, String> {

}
