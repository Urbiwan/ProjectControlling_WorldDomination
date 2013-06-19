package edu.hm.dropwizard.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.palo.IPaloControl;

@Path("/compute")
public class ComputeResource {
    private final IPaloControl palo;

    public  ComputeResource(IPaloControl palo) {
        this.palo = palo;
    }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONResponse compute(JSONRequest data)
	{
		return AnalyzeConverter.convert(palo.compute(BookingFactory.create(data)));
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONResponse compute(@QueryParam("token") int token)
    {
        return AnalyzeConverter.convert(palo.compute(token));
    }
}
