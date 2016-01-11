package api.resources;

import api.exceptionMapping.UnknownAggregateRootExceptionMapper;
import api.representations.PrioSessionDto;
import domain.UnknownAggregateRootException;
import domain.sessions.PrioResult;
import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import domain.votes.VoteService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PrioSessionResourceTest {
    private static final PrioSessionRepo repo = mock(PrioSessionRepo.class);
    private static final VoteService service = mock(VoteService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PrioSessionResource(repo, service))
            .addProvider(UnknownAggregateRootExceptionMapper.class)
            .build();

    private final PrioSession session = PrioSession.newSession().build();

    @Before
    public void setup() throws UnknownAggregateRootException {
        when(repo.findById(anyString())).thenReturn(session);
    }

    @After
    public void tearDown(){
        reset(repo);
    }

    @Test
    public void getsSingleSession() throws UnknownAggregateRootException {
        PrioSessionDto dto = request("/prioSession/123").get(PrioSessionDto.class);
        assertEquals(session.getId(), dto.getId());
        verify(repo).findById("123");
    }

    @Test
    public void returns404OnNonExistingSession() throws Exception {
        when(repo.findById("123")).thenThrow(new UnknownAggregateRootException());

        Response r = request("/prioSession/123").get();

        assertEquals(404, r.getStatus());
    }

    private Invocation.Builder request(String path) {
        return resources.client().target(path).request();
    }

    @Test
    public void prioritizesSession() throws Exception {

        PrioResult expectedResult = PrioResult.error("");

        when(service.resolvePrioritiesForSession("123")).thenReturn(expectedResult);

        PrioResult actualResult = request("/prioSession/123/rankedPrios").get(PrioResult.class);
 //ToDo

    }
}
