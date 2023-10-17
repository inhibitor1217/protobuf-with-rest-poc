package io.inhibitor.protobufwithrest.pocserver;

import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.inhibitor.protobufwithrest.pocserver.services.user.UserModule;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class POCServerApplication extends Application<POCServerConfiguration> {

  public static void main(String[] args) throws Exception {
    new POCServerApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<POCServerConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
            new EnvironmentVariableSubstitutor(false)));

    bootstrap.addBundle(
        GuiceBundle.builder()
            .enableAutoConfig(
                "io.inhibitor.protobufwithrest.pocserver.behaviors",
                "io.inhibitor.protobufwithrest.pocserver.resources",
                "io.inhibitor.protobufwithrest.pocserver.services"
            )
            .modules(
                new UserModule()
            )
            .build()
    );
  }

  @Override
  public void run(POCServerConfiguration configuration, Environment environment) {}
}
