package edu.hm.model.bookings;

import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.model.bookings.model.bookings.impl.AccountingData;
import edu.hm.model.bookings.model.bookings.impl.Employee;
import edu.hm.model.bookings.model.bookings.impl.Entry;
import edu.hm.model.bookings.model.bookings.impl.Project;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class BookingFactory {

    public static IAccountingData create(Object obj) {
        if(obj.getClass() == JSONRequest.class)
            return create((JSONRequest)obj);

        throw new IllegalArgumentException("Unknown object.");
    }

    private static IAccountingData create(JSONRequest request) {
       AccountingData data = new AccountingData(new ArrayList<Entry>(), new ArrayList<Employee>(),
               new ArrayList<String>(), new ArrayList<Project>());

        for(JSONChild child : request.getEntries()) {
            if(!data.getModifiableDepartments().contains(child.getDepartment()))
                data.getModifiableDepartments().add((child.getDepartment()));

            Project project = data.getModifiableProject(child.getProject());
            if(project == null) {
                project = new Project(child.getProject(), child.getDepartment(), new ArrayList<String>());
                project.getModifiableList().add(child.getAccount());
                data.getModifiableProjects().add(project);
            }
            if(!project.getModifiableList().contains(child.getAccount()))
                project.getModifiableList().add(child.getAccount());

            Employee employee = data.getModifiableEmployee(child.getEmployeeID());
            if(employee == null) {
                employee = new Employee(child.getEmployeeID(), child.getEmployee(), child.getEmployeeTier());
                data.getModifiableEmployees().add(employee);
            }

            Entry entry = new Entry(employee, child.getHours(), child.getMonth(), child.getYear(), project,
                    child.getAccount(), child.isFakt(), child.getCostLimit(), child.getCostRate());
            data.getModifiableBookings().add(entry);
        }

        return data;
    }
}
