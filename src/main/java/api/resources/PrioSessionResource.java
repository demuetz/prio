package api.resources;

import api.representations.PrioSessionDto;
import domain.PrioSession;
import domain.PrioSessionRepo;

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

    private final PrioSessionRepo repo;

    public PrioSessionResource(PrioSessionRepo repo) {
        this.repo = repo;
    }

    @GET
    public Response getAll(){

        List<PrioSession> sessions = repo.getAll();

        List<PrioSessionDto> dtos = new ArrayList<PrioSessionDto>();

        for (PrioSession s : sessions){
            dtos.add(PrioSessionDto.fromSession(s));
        }

        return Response.ok(dtos).build();
    }
}
