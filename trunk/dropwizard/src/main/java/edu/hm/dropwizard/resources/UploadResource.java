package edu.hm.dropwizard.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import edu.hm.dropwizard.URICoding;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.palo.IPaloControl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONToken compute(@FormDataParam("file") InputStream uploadedInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JSONRequest request = mapper.readValue(uploadedInputStream, JSONRequest.class);
            return new JSONToken(palo.upload(BookingFactory.create(request)));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONToken(-1);
        }
    }
}
