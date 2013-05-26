package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	@JsonProperty("Name")
	private String name;
	
	public Account(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
