package edu.hm.dropwizard.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.dropwizard.excel.ExcelParser;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.model.bookings.IAccountingData;
import edu.hm.palo.IPaloControl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        int token = -1;

        try {
            IAccountingData data = BookingFactory.create(ExcelParser.getData(uploadedInputStream));
            token = palo.upload(data);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return new JSONToken(token);
    }
}
