package com.test.timeentry.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.timeentry.entity.TimeEntry;
import com.test.timeentry.repo.TimeEntryRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeEntryService {

	@Autowired
	TimeEntryRepo timeEntryRepo;

	TimeEntry timeEntry = new TimeEntry();

	public TimeEntry addEntries(TimeEntry timeEntry) {
		log.info("Adding new time entry");
		
		if (Duration.between(timeEntry.getInTime(), timeEntry.getOutTime()).isNegative()) {
			LocalTime outTime = timeEntry.getOutTime().plusHours(12);
			timeEntry.setOutTime(outTime);
		} 
		LocalTime duration = timeEntry.getOutTime().minusHours(timeEntry.getInTime().getHour()).minusMinutes(timeEntry.getInTime().getMinute());
		System.out.println("Time Difference: "+ duration);
		timeEntry.setDuration(duration);
		return timeEntryRepo.save(timeEntry);
		
	}

	public List<TimeEntry> getAllEntries() {
		log.info("Fetching all time entries");
		return timeEntryRepo.findAll();
	}

	public String deleteEntry(long id) {
		
		if (timeEntryRepo.findById(id).isPresent()) {
			timeEntryRepo.deleteById(id);
			log.info("Deleting time entry with id:"+id);
			return "Time Entry Deleted";
		} else
			throw new NullPointerException("id " + id + " does not exist");
	}

	public LocalTime getFirstIn() {
		log.info("Getting first in-time entry");
		return timeEntryRepo.getFirstIn();
	}

	public LocalTime getLastOut() {
		log.info("Getting last out-time entry");
		return timeEntryRepo.getLastOut();
	}

	public LocalTime getInHours() {
		log.info("Getting total in-hours");
		LocalTime totalInHours = LocalTime.MIDNIGHT;
		List<TimeEntry> timeEntries = timeEntryRepo.findAll();
		for (TimeEntry timeEntry : timeEntries) {
			LocalTime lt = timeEntry.getDuration();
			totalInHours = totalInHours.plusHours(lt.getHour()).plusMinutes(lt.getMinute());
			log.info("Time in hours"+totalInHours);
		}
		return totalInHours;
	}

	public LocalTime getOutHours() {
		log.info("Getting total out-hours");
		LocalTime totalInHours = LocalTime.MIDNIGHT;
		List<TimeEntry> timeEntries = timeEntryRepo.findAll();
		for (TimeEntry timeEntry : timeEntries) {
			LocalTime lt = timeEntry.getDuration();
			totalInHours = totalInHours.plusHours(lt.getHour()).plusMinutes(lt.getMinute());
			log.info("Time in hours"+totalInHours);
		}
		LocalTime lastOut = timeEntryRepo.getLastOut();
		LocalTime firstIn = timeEntryRepo.getFirstIn();
		LocalTime difference = lastOut.minusHours(firstIn.getHour()).minusMinutes(firstIn.getMinute());
		LocalTime totalOutHours = totalInHours.minusHours(difference.getHour()).minusMinutes(difference.getMinute());
		return totalOutHours;
	}
}
