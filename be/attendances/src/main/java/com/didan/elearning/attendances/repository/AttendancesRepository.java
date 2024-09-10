package com.didan.elearning.attendances.repository;

import com.didan.elearning.attendances.entity.Attendances;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendancesRepository extends JpaRepository<Attendances, String> {
  List<Attendances> findAttendancesByClassCodeIgnoreCase(String classCode);

}
