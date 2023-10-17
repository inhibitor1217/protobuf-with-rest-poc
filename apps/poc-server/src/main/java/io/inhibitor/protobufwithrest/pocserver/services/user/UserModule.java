package io.inhibitor.protobufwithrest.pocserver.services.user;

import com.google.inject.AbstractModule;

public class UserModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(UserService.class).to(StubUserService.class);
  }
}
