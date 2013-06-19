package edu.hm.dropwizard.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.dropwizard.URICoding;
import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.model.bookings.*;
import edu.hm.palo.IPaloControl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qriz
 * Date: 19.06.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
@Path("download")
public class DownloadResource {
    private final IPaloControl palo;

    public DownloadResource(IPaloControl palo) {
        this.palo = palo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONRequest compute(@QueryParam("token") int token)
    {
        List<JSONChild> children = new ArrayList<>();

        IAccountingData data = palo.getData(token);
        for(IEntry entry : data.getBookings()) {
            IEmployee employee = entry.getEmployee();
            IProject project = entry.getProject();

            JSONChild child = new JSONChild(employee.getId(), employee.getName(), employee.getDevelopmentTier(),
                    entry.getHours(), entry.getMonth(), entry.getYear(), project.getName(), project.getDepartment(), entry.getAccount(),
                    entry.isFakt(), entry.getCostLimit(), entry.getCostRate());
            children.add(child);
        }

        return new JSONRequest(children.toArray(new JSONChild[0]));
    }
}
