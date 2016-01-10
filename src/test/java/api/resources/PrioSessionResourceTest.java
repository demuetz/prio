package api.resources;

import api.representations.PrioSessionDto;
import domain.UnknownAggregateRootException;
import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PrioSessionResourceTest {
    private static final PrioSessionRepo dao = mock(PrioSessionRepo.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PrioSessionResource(dao))
            .build();

    private final PrioSession session = PrioSession.newSession().build();

    @Before
    public void setup() throws UnknownAggregateRootException {
        when(dao.findById(anyString())).thenReturn(session);
    }

    @After
    public void tearDown(){
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }

    @Test
    public void getsSingleSession() throws UnknownAggregateRootException {
        PrioSessionDto dto = resources.client().target("/prioSession/blah").request().get(PrioSessionDto.class);
        assertEquals(session.getId(), dto.getId());
    }


}
