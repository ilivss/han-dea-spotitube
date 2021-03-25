package han.oose.dea.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("healthcheck")
public class HealthCheck {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkHealth() {
        return Response.ok("werkt!").build();
    }
}
