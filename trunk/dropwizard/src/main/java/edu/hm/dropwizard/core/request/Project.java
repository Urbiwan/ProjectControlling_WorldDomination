package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
	@JsonProperty("Name")
	private String name;
	
	public Project(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
