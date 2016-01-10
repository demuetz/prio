package api.exceptionMapping;

import domain.UnknownAggregateRootException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnknownAggregateRootExceptionMapper implements ExceptionMapper<UnknownAggregateRootException>{
    @Override
    public Response toResponse(UnknownAggregateRootException e) {
        return Response.status(404).build();
    }
}
