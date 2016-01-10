package api.resources;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PrioSessionResourceTest {
    private static final PrioSessionRepo repo = mock(PrioSessionRepo.class);
    private static final VoteService service = mock(VoteService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PrioSessionResource(repo, service))
            .build();

    private final PrioSession session = PrioSession.newSession().build();

    @Before
    public void setup() throws UnknownAggregateRootException {
        when(repo.findById(anyString())).thenReturn(session);
    }

    @After
    public void tearDown(){
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(repo);
    }

    @Test
    public void getsSingleSession() throws UnknownAggregateRootException {
        PrioSessionDto dto = request("/prioSession/123").get(PrioSessionDto.class);
        assertEquals(session.getId(), dto.getId());
        verify(repo).findById("123");
    }

    private Invocation.Builder request(String path) {
        return resources.client().target(path).request();
    }

    @Test
    public void prioritizesSession() throws Exception {

        PrioResult expectedResult = new PrioResult();

        when(service.resolvePrioritiesForSession("123")).thenReturn(expectedResult);

        PrioResult actualResult = request("/prioSession/123/priorities").get(PrioResult.class);


    }
}
