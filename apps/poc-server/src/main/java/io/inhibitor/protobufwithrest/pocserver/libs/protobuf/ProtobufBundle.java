package io.inhibitor.protobufwithrest.pocserver.libs.protobuf;

import io.dropwizard.core.Configuration;
import io.dropwizard.core.ConfiguredBundle;
import io.dropwizard.core.setup.Environment;

public class ProtobufBundle<C extends Configuration> implements ConfiguredBundle<C> {

  @Override
  public void run(C configuration, Environment environment) {
    environment.jersey().register(ProtobufMessageBodyReader.class);
    environment.jersey().register(ProtobufMessageBodyWriter.class);
    environment.jersey().register(ProtobufJsonMessageBodyWriter.class);
  }
}
