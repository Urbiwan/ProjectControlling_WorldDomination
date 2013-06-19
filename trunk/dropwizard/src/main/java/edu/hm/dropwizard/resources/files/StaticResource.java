package edu.hm.dropwizard.resources.files;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: qriz
 * Date: 19.06.13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
@Path("/{file}")
public class StaticResource {

    @GET
    public StreamingOutput getFile(@PathParam("file") final String file){
        return new StreamingOutput(){
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException{
                try(InputStream stream = getClass().getResourceAsStream("/"+file)) {
                    IOUtils.copy(stream, output);
                }
            }

        };
    }
}
