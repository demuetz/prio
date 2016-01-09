package api.resources;

import api.representations.Vote;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/vote")
public class VoteResource {

    
    @POST
    public Response cast(Vote v){



        return Response.accepted().build();
    }
}