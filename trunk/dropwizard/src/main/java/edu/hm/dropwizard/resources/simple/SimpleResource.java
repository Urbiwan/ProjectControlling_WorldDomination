package edu.hm.dropwizard.resources.simple;

import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.model.bookings.BookingFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 17.06.13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
@Path("/simple")
public class SimpleResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONOutput compute(JSONInput data)
    {
        return new JSONOutput(data.getInput()*data.getInput());
    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONOutput simple(JSONInput data)
    {
        return new JSONOutput(data.getInput()*data.getInput());
    }
}
