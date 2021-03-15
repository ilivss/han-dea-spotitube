package han.oose.dea.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("healthcheck")
public class HealthCheck {
    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkHealth() {
        return Response.status(200).entity("Werkt!").build();
    }
}
