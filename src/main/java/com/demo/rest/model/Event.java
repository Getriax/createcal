package com.demo.rest.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String description;
	@NotNull
	private Date date;
	@NotNull 
	Time time;
	@Range(min = 1, max = 3)
	private Integer important;
	private String tag;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Event() {
	}
	public Event(String description, Date date, Time time, Integer important, String tag) {
		this.description = description;
		this.date = date;
		this.time = time;
		this.important = important;
		this.tag = tag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Integer getImportant() {
		return important;
	}
	public void setImportant(Integer important) {
		this.important = important;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", description=" + description + ", dateTime=" + date + "-" + time + ", important="
				+ important + ", user=" + user + "]";
	}
	
}
