package edu.hm.dropwizard.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
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
}
