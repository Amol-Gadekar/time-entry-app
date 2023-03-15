package com.test.timeentry.repo;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.timeentry.entity.TimeEntry;

@Repository
public interface TimeEntryRepo extends JpaRepository<TimeEntry, Long>{

	@Query(value = "SELECT MIN(in_time) FROM time_entry;", nativeQuery = true)
	LocalTime getFirstIn();

	@Query(value = "SELECT MAX(out_time) FROM time_entry;", nativeQuery = true)
	LocalTime getLastOut();

	@Query(value = "SELECT SUM(duration_in_millis/1000000) FROM time_entry;", nativeQuery = true)
	Duration getInHours();
	
	
	
}
