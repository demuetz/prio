package api.resources;

import dataAccess.InMemoryPrioSessionRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prioSession")
@Produces({ MediaType.APPLICATION_JSON })
public class PrioSessionResource {

    private final InMemoryPrioSessionRepo repo;

    public PrioSessionResource(InMemoryPrioSessionRepo repo) {
        this.repo = repo;
    }

    @GET
    public Response getAll(){

        return Response.ok(repo.getAll().toArray()).build();
    }



}
