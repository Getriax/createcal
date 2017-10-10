package com.demo.rest;

public class Greeter {
	private final long id; 
	private final String content;
	public Greeter(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
}
