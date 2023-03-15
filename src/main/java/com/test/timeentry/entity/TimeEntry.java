package com.test.timeentry.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "in-time cannot be null")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime inTime;
	@NotNull(message = "out-time cannot be null")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime outTime;
	private LocalTime duration;
	
}
