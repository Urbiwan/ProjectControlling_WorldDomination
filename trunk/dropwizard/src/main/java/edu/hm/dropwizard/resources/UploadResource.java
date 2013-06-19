package edu.hm.dropwizard.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.dropwizard.URICoding;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.palo.IPaloControl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: qriz
 * Date: 19.06.13
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
@Path("/upload")
public class UploadResource {
    private final IPaloControl palo;

    public  UploadResource(IPaloControl palo) {
        this.palo = palo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONToken compute(@QueryParam("file") String file)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JSONRequest request = mapper.readValue(URICoding.decodeURIComponent(file), JSONRequest.class);
            return new JSONToken(palo.upload(BookingFactory.create(request)));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONToken(-1);
        }
    }
}
