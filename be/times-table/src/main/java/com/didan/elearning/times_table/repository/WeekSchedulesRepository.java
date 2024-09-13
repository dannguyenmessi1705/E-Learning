package com.didan.elearning.times_table.repository;

import com.didan.elearning.times_table.entity.WeekSchedules;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekSchedulesRepository extends JpaRepository<WeekSchedules, String> {
  List<WeekSchedules> findWeekSchedulesBySemesterCodeIgnoreCase(String semesterCode);
  @Transactional
  @Modifying
  void deleteAllBySemesterCodeIgnoreCase(String semesterCode);
}
