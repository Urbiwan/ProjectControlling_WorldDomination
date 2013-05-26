package edu.hm.dropwizard.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.dropwizard.core.request.AccountingData;
import edu.hm.dropwizard.core.response.ComputedData;

@Path("/compute")
public class ComputeResource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComputedData compute(AccountingData data)
	{
		
		return null;
	}
}
