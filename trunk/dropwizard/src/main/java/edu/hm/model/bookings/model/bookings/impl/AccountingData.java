package edu.hm.model.bookings.model.bookings.impl;

import java.util.Collections;
import java.util.List;

import edu.hm.model.bookings.IAccountingData;

public class AccountingData implements IAccountingData {
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

	public List<Entry> getBookings() {
		return Collections.unmodifiableList(booking);
	}
    public List<Entry> getModifiableBookings() {
        return booking;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }
    public List<Employee> getModifiableEmployees() {
        return employees;
    }

    public List<String> getDepartments() {
        return Collections.unmodifiableList(departments);
    }
    public List<String> getModifiableDepartments() {
        return departments;
    }

    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }
    public List<Project> getModifiableProjects() {
        return projects;
    }

    /**
     * Return a Project, with searched name, or null when not found.
     * @param name Project-Name.
     * @return Project.
     */
    public Project getModifiableProject(String name) {
        for(Project project : projects) {
            if(project.getName().equals(name))
                return project;
        }

        return null;
    }

    public Employee getModifiableEmployee(int employeeID) {
        //This code can be extracted.
        for(Employee employee: employees) {
            if(employee.getId() == employeeID) {
                return employee;
            }
        }

        return null;
    }
}
