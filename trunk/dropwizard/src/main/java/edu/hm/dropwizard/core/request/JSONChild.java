package edu.hm.dropwizard.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class JSONChild {
    @JsonProperty("MA-ID")
    private int employeeID;

    @JsonProperty("Employee")
    private String employee;

    @JsonProperty("EmployeeTier")
    private int employeeTier;

    @JsonProperty("Hours")
    private float hours;

    @JsonProperty("Month")
    private int month;

    @JsonProperty("Year")
    private int year;

    @JsonProperty("Project")
    private String project;

    @JsonProperty("Department")
    private String department;

    @JsonProperty("Account")
    private String account;

    @JsonProperty("Fakt")
    private boolean fakt;

    @JsonProperty("CostLimit")
    private float costLimit;

    @JsonProperty("CostRate")
    private float costRate;

    public JSONChild() {

    }

    public JSONChild(int employeeID, String employee, int employeeTier, float hours, int month, int year,
                     String project, String department, String account, boolean fakt,
                     float costLimit, float costRate) {
        this.employeeID = employeeID;
        this.employee = employee;
        this.employeeTier = employeeTier;
        this.hours = hours;
        this.month = month;
        this.year = year;
        this.project = project;
        this.department = department;
        this.account = account;
        this.fakt = fakt;
        this.costLimit = costLimit;
        this.costRate = costRate;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployee() {
        return employee;
    }

    public int getEmployeeTier() {
        return employeeTier;
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

    public String getProject() {
        return project;
    }

    public String getDepartment() {
        return department;
    }

    public String getAccount() {
        return account;
    }

    public boolean isFakt() {
        return fakt;
    }

    public float getCostLimit() {
        return costLimit;
    }

    public float getCostRate() {
        return costRate;
    }
}
