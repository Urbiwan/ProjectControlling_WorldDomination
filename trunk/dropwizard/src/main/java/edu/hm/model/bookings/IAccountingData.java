package edu.hm.model.bookings;

import edu.hm.model.bookings.model.bookings.impl.Employee;
import edu.hm.model.bookings.model.bookings.impl.Entry;
import edu.hm.model.bookings.model.bookings.impl.Project;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public interface IAccountingData {
    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<IEntry> getBookings();

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<IEmployee> getEmployees();

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<String> getDepartments();

    /**
     * Return an immutable List.
     * @return immutable List.
     */
    public List<IProject> getProjects();

    public IEmployee getEmployeeById(int id);

    public IProject getProjectByName(String name);
}
