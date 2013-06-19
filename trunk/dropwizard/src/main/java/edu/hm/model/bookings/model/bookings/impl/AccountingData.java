package edu.hm.model.bookings.model.bookings.impl;

import java.util.Collections;
import java.util.List;

import edu.hm.model.bookings.IAccountingData;
import edu.hm.model.bookings.IEmployee;
import edu.hm.model.bookings.IEntry;
import edu.hm.model.bookings.IProject;

public class AccountingData implements IAccountingData {
	private final List<? extends IEntry> booking;
    private final List<? extends IEmployee> employees;
    private final List<String> departments;
    private final List<? extends IProject> projects;

    public AccountingData(List<Entry> entries, List<Employee> employees, List<String> departments, List<Project> projects) {
        //TODO Add guard.
        this.booking = entries;
        this.employees = employees;
        this.departments = departments;
        this.projects = projects;
    }

	public List<IEntry> getBookings() {
		return Collections.unmodifiableList(booking);
	}
    public List<Entry> getModifiableBookings() {
        return (List<Entry>)booking;
    }

    public List<IEmployee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }
    public List<Employee> getModifiableEmployees() {
        return (List<Employee>) employees;
    }

    public List<String> getDepartments() {
        return Collections.unmodifiableList(departments);
    }
    public List<String> getModifiableDepartments() {
        return departments;
    }

    public List<IProject> getProjects() {
        return Collections.unmodifiableList(projects);
    }

    public List<Project> getModifiableProjects() {
        return (List<Project>) projects;
    }

    public IEmployee getEmployeeById(int id) {
        for(IEmployee employee : getEmployees()) {
            if(employee.getId() == id)
                return employee;
        }

        return null;
    }

    public IProject getProjectByName(String name) {
        for(IProject project : getProjects()) {
            if(project.getName().equals(name))
                return project;
        }

        return null;
    }

    /**
     * Return a Project, with searched name, or null when not found.
     * @param name Project-Name.
     * @return Project.
     */
    public Project getModifiableProject(String name) {
        for(Project project : getModifiableProjects()) {
            if(project.getName().equals(name))
                return project;
        }

        return null;
    }

    public Employee getModifiableEmployee(int employeeID) {
        //This code can be extracted.
        for(Employee employee: getModifiableEmployees()) {
            if(employee.getId() == employeeID) {
                return employee;
            }
        }

        return null;
    }
}
