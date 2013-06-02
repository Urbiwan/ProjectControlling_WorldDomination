package edu.hm.model.bookings;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountingData {
	private final List<Entry> booking;
    private final List<Employee> employees;
    private final List<String> departments;
    private final List<Project> projects;

    public AccountingData(List<Entry> entries, List<Employee> employees, List<String> departments, List<Project> projects) {
        //TODO Add guard.
        this.booking = entries;
        this.employees = employees;
        this.departments = departments;
        this.projects = projects;
    }
	
	/**
	 * Return an immutable List.
	 * @return immutable List.
	 */
	public List<Entry> getBookings() {
		return Collections.unmodifiableList(booking);
	}

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<String> getDepartments() {
        return Collections.unmodifiableList(departments);
    }

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }
}
