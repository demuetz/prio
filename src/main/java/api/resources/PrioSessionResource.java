package api.resources;

import api.representations.PrioSessionDto;
import api.representations.VoteDto;
import domain.*;

import javax.ws.rs.*;
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

    @POST
    @Path("{id}/vote")
    public Response cast(@PathParam("id") String id, VoteDto voteDto) throws UnknownAggregateRootException {

        Participant participant = Participant.withName(voteDto.getUserName());
        VotedOptions options = VotedOptions.withIds(voteDto.getOrderedOptions());
        new VoteService(repo).castVoteForSession(new domain.Vote(participant, options), id);

        return Response.accepted().build();
    }
}
