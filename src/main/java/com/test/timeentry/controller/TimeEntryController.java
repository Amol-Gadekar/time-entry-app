package com.test.timeentry.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.timeentry.entity.TimeEntry;
import com.test.timeentry.service.TimeEntryService;

@RestController
public class TimeEntryController {

	@Autowired
	TimeEntryService timeEntryService;
	
	@PostMapping("/addEntries")
	private TimeEntry addEntries(@RequestBody @Valid TimeEntry timeEntry){
		return timeEntryService.addEntries(timeEntry);
	}
	
	@GetMapping("/getAllEntries")
	private List<TimeEntry> getAllEntries() {
		return timeEntryService.getAllEntries();
	}
	
	@DeleteMapping("/deleteEntry/{id}")
	private String deleteEntry(@PathVariable long id) {
		return timeEntryService.deleteEntry(id);
	}
	
	@GetMapping("/getFirstIn")
	private LocalTime getFirstIn() {
		return timeEntryService.getFirstIn();
	}
	
	@GetMapping("/getLastOut")
	private LocalTime getLastOut() {
		return timeEntryService.getLastOut();
	}
	
	@GetMapping("/getInHours")
	private LocalTime getInHours() {
		return timeEntryService.getInHours();
	}
	
	@GetMapping("/getOutHours")
	private LocalTime getOutHours() {
		return timeEntryService.getOutHours();
	}
}
