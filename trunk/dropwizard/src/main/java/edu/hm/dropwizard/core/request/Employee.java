package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Tier")
	private int developTier;
	
	public Employee(String name, int developTier) {
		this.name = name;
		this.developTier = developTier;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDevelopmentTier() {
		return developTier;
	}
}
