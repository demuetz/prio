package api.resources;

import api.representations.PrioSessionDto;
import api.representations.Vote;
import com.google.common.base.Optional;
import dataAccess.InMemoryPrioSessionRepo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/vote")
public class VoteResource {


    private final InMemoryPrioSessionRepo sessionRepo;

    public VoteResource(InMemoryPrioSessionRepo sessionRepo) {

        this.sessionRepo = sessionRepo;
    }

    @POST
    public Response cast(Vote vote){

        Optional<PrioSessionDto> s = sessionRepo.findByKey(vote.getSessionKey());

        if (!s.isPresent())
            return Response.status(403).build();

        PrioSessionDto session = s.get();



        return Response.accepted().build();
    }
}