package edu.hm.model.bookings;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private final int id;
	private final String name;
	private final int developTier;
	
	public Employee(int id, String name, int developTier) {
        //TODO Add guard.
        this.id = id;
		this.name = name;
		this.developTier = developTier;
	}

    public  int getId() {
        return id;
    }

	public String getName() {
		return name;
	}
	
	public int getDevelopmentTier() {
		return developTier;
	}
}
