package edu.hm.model.bookings;

public class Entry {
	private final float hours;
	private final int month;
    private final int year;

    private final Project project;
    private final String account;

	private final boolean fakt;
	private final int borderCosts;
	private final float costRate;

    public Entry(float hours, int month, int year, Project project, String account, boolean fakt, int borderCosts, float costRate) {
        //TODO Add guard.
        this.hours = hours;
        this.month = month;
        this.year = year;
        this.project = project;
        this.account = account;
        this.fakt = fakt;
        this.borderCosts = borderCosts;
        this.costRate = costRate;
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
	
	public int getBorderCosts() {
		return borderCosts;
	}
	
	public float getCostRate() {
		return costRate;
	}
}
