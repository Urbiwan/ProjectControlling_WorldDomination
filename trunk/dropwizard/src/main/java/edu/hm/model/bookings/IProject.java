package edu.hm.model.bookings;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public interface IProject {

    public String getName();

    public String getDepartment();

    public List<String> getAccounts();
}
