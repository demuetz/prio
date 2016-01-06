package api.resources;

import api.representations.PrioSession;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/prioSession")
@Produces({ MediaType.APPLICATION_JSON })
public class PrioSessionResource {

    @GET
    public Response getAll(){
        List<PrioSession> res = new ArrayList<PrioSession>();

        res.add(PrioSession.withKey("124"));

        return Response.ok(res.toArray()).build();
    }

}
