package com.didan.elearning.times_table.repository;

import com.didan.elearning.times_table.entity.DateSchedules;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateSchedulesRepository extends JpaRepository<DateSchedules, String> {
  Optional<DateSchedules> findDateSchedulesByDate(LocalDate date);
  List<DateSchedules> findDateSchedulesByWeekSchedules_WeekNumberAndWeekSchedules_SemesterCode(int weekNumber, String semesterCode);
}
