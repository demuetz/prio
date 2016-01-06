package api;

import api.resources.PrioSessionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PrioApplication extends Application<PrioConfiguration> {

    public static void main(String[] args) throws Exception {
        new PrioApplication().run(args);
    }

    @Override
    public void run(PrioConfiguration prioConfiguration, Environment environment) throws Exception {

        environment.jersey().register(new PrioSessionResource());

    }
}
