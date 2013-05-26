package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {
	@JsonProperty("Hour")
	private int hours;
	@JsonProperty("Month")
	private int month;
	@JsonProperty("Fakt")
	private boolean fakt;
	@JsonProperty("BorderCosts")
	private int borderCosts;
	@JsonProperty("CostRate")
	private int costRate;
	
	@JsonProperty("Employee")
	private Employee employee;
	
	@JsonProperty("Project")
	private Project project;
	
	@JsonProperty("Account")
	private Account account;
	
	public int getHours() {
		return hours;
	}
	
	public int getMonth() {
		return month;
	}
	
	public boolean isFakt() {
		return fakt;
	}
	
	public int getBorderCosts() {
		return borderCosts;
	}
	
	public int getCostRate() {
		return costRate;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Project getProject() {
		return project;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public Entry() {
	
	}
}
