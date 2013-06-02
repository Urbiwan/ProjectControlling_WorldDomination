package edu.hm.model.bookings;

import java.util.Collections;
import java.util.List;

public class Project {
	private final String name;
    private final String department;
    private final List<String> accounts;
	
	public Project(String name, String department, List<String> accounts) {
        //TODO Add guard.
		this.name = name;
        this.department = department;
        this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

    public String getDepartment() {
        return department;
    }

    public  List<String> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}
