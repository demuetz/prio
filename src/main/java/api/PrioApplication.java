package api;

import api.exceptionMapping.UnknownAggregateRootExceptionMapper;
import api.resources.PrioSessionResource;
import dataAccess.InMemoryPrioSessionRepo;
import domain.votes.VoteService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PrioApplication extends Application<PrioConfiguration> {

    public static void main(String[] args) throws Exception {
        new PrioApplication().run(args);
    }

    @Override
    public void run(PrioConfiguration prioConfiguration, Environment environment) throws Exception {

        InMemoryPrioSessionRepo sessionRepo = new InMemoryPrioSessionRepo();
        VoteService service = new VoteService(sessionRepo);
        environment.jersey().register(new PrioSessionResource(sessionRepo, service));
        environment.jersey().register(new UnknownAggregateRootExceptionMapper());
    }
}
