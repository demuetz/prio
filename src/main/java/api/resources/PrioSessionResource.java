package api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/prioSession")
public class PrioSessionResource {

    @GET
    public String getAll(){
        return "bla";
    }

}
