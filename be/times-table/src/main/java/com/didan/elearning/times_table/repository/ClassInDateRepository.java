package com.didan.elearning.times_table.repository;

import com.didan.elearning.times_table.entity.ClassInDate;
import com.didan.elearning.times_table.entity.DateSchedules;
import com.didan.elearning.times_table.entity.TimeClasses;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassInDateRepository extends JpaRepository<ClassInDate, String> {
  List<ClassInDate> findClassInDateByTimeClasses(TimeClasses timeClasses);
  List<ClassInDate> findClassInDateByDateSchedules(DateSchedules dateSchedules);
  Optional<ClassInDate> findClassInDateByTimeClassesAndDateSchedules(TimeClasses timeClasses, DateSchedules dateSchedules);
}
