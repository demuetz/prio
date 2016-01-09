package api.resources;

import api.representations.PrioItemDto;
import api.representations.PrioSessionDto;
import api.representations.VoteDto;
import domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/prioSession")
@Produces({ MediaType.APPLICATION_JSON })
public class PrioSessionResource {

    @Context
    UriInfo uriInfo;

    private final PrioSessionRepo repo;

    public PrioSessionResource(PrioSessionRepo repo) {
        this.repo = repo;
    }

    @GET
    public Response getAll(){

        List<PrioSessionDto> dtos = repo.getAll().stream().map(PrioSessionDto::fromSession).collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) throws UnknownAggregateRootException {
        PrioSession s = repo.findById(id);

        return Response.ok(PrioSessionDto.fromSession(s)).build();
    }

    @POST
    public Response create(PrioSessionDto dto){

        PrioSession s = PrioSession.create();

        String[] itemTexts = dto.getItems().stream().map(PrioItemDto::getText).toArray(String[]::new);

        s.addItems(PrioItems.fromTexts(itemTexts));

        repo.create(s);

        URI location = uriInfo.getAbsolutePathBuilder().path(s.getId()).build();

        return Response.created(location).build();
    }


    @POST
    @Path("{id}/vote")
    public Response vote(@PathParam("id") String id, VoteDto voteDto) throws UnknownAggregateRootException {

        Participant participant = Participant.withName(voteDto.getUserName());
        VotedOptions options = VotedOptions.withIds(voteDto.getOrderedOptions());
        new VoteService(repo).castVoteForSession(new domain.Vote(participant, options), id);

        return Response.accepted().build();
    }
}
