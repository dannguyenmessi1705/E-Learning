package com.didan.elearning.attendances.repository;

import com.didan.elearning.attendances.entity.AttendanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRecordsRepository extends JpaRepository<AttendanceRecords, String> {

}
