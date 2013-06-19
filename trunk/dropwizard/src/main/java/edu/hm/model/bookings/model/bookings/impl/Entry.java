package edu.hm.model.bookings.model.bookings.impl;

import edu.hm.model.bookings.IEmployee;
import edu.hm.model.bookings.IEntry;

public class Entry implements IEntry {
	private final float hours;
	private final int month;
    private final int year;

    private final Project project;
    private final String account;
    private final Employee employee;

	private final boolean fakt;
	private final float borderCosts;
	private final float costRate;

    public Entry(Employee employee, float hours, int month, int year, Project project, String account, boolean fakt, float borderCosts, float costRate) {
        //TODO Add guard.
        this.employee = employee;
        this.hours = hours;
        this.month = month;
        this.year = year;
        this.project = project;
        this.account = account;
        this.fakt = fakt;
        this.borderCosts = borderCosts;
        this.costRate = costRate;
    }

    @Override
    public IEmployee getEmployee() {
        return employee;
    }

    public float getHours() {
		return hours;
	}
	
	public int getMonth() {
		return month;
	}

    public int getYear() {
        return year;
    }

    public Project getProject() {
        return project;
    }

    public String getAccount() {
        return account;
    }
	
	public boolean isFakt() {
		return fakt;
	}
	
	public float getCostLimit() {
		return borderCosts;
	}
	
	public float getCostRate() {
		return costRate;
	}
}
