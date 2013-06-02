package edu.hm.model.bookings;

import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class BookingFactory {

    public static AccountingData create(Object obj) {
        if(obj.getClass() == JSONRequest.class)
            return create((JSONRequest)obj);

        throw new IllegalArgumentException("Unknown object.");
    }


    //TODO replace AccountingData with Interface, which is readonly.
    private static AccountingData create(JSONRequest request) {
        List<Entry> entries = new ArrayList<Entry>();
        List<Project> projects = new ArrayList<Project>();
        List<Employee> employees = new ArrayList<Employee>();
        List<String> departments = new ArrayList<String>();


        for(JSONChild child : request.getEntries()) {
            //TODO add functionality

            //Project project = new Project()

            //Entry entry = new Entry(child.getHours(), child.getMonth(), )

        }

        return new AccountingData(entries, employees, departments, projects);
    }
}
