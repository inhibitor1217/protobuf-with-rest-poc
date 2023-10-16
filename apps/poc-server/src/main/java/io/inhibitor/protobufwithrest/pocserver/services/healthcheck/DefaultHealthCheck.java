package io.inhibitor.protobufwithrest.pocserver.services.healthcheck;

import ru.vyarus.dropwizard.guice.module.installer.feature.health.NamedHealthCheck;

public class DefaultHealthCheck extends NamedHealthCheck {

  @Override
  public String getName() {
    return "default";
  }

  @Override
  protected Result check() {
    return Result.healthy();
  }
}
