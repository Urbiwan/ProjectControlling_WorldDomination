package edu.hm.dropwizard.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONToken;
import edu.hm.model.bookings.BookingFactory;
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

        try {
            JSONRequest request = null;

            Workbook wb = WorkbookFactory.create(uploadedInputStream);
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Do something here
                }
            }

            return new JSONToken(palo.upload(BookingFactory.create(request)));
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
