package api.resources;

import api.representations.PrioItemDto;
import api.representations.PrioResultDto;
import api.representations.PrioSessionDto;
import api.representations.VoteDto;
import domain.*;
import domain.sessions.PrioItems;
import domain.sessions.Ranking;
import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import domain.votes.Vote;
import domain.votes.VoteService;
import domain.votes.VotedOptions;

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
    private final VoteService service;

    public PrioSessionResource(PrioSessionRepo repo, VoteService service) {
        this.repo = repo;
        this.service = service;
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

        PrioSession s = PrioSession.newSession().build();

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

        service.castVoteForSession(new Vote(participant, options), id);

        return Response.accepted().build();
    }

    @GET
    @Path("{id}/rankedPrios")
    public PrioResultDto getCurrentPriorities(@PathParam("id") String id) throws UnknownAggregateRootException {
        Ranking prioResult = service.resolvePrioritiesForSession(id);
        return new PrioResultDto(new int[0]);
    }
}
