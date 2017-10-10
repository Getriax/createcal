package com.demo.rest.model;

import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class SimpleEvent {
	@NotBlank
	private String description;
	@NotNull
	private Date date;
	@NotNull 
	String timeString;
	@Range(min = 1, max = 3)
	private Integer important;
	private String tag;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public Integer getImportant() {
		return important;
	}
	public void setImportant(Integer important) {
		this.important = important;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Event getEvent() {
		return new Event(description, date, Time.valueOf(timeString + ":00"), important, tag);
	}
}
