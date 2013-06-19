package edu.hm.model.bookings;

import edu.hm.model.bookings.model.bookings.impl.Project;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public interface IEntry {

    public IEmployee getEmployee();

    public float getHours();

    public int getMonth();

    public int getYear();

    public Project getProject();

    public String getAccount();

    public boolean isFakt();

    public float getCostLimit();

    public float getCostRate();
}
