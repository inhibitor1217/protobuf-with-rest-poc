package io.inhibitor.protobufwithrest.pocserver.services.user;

import io.inhibitor.protobufwithrest.poc.service.user.GetUserRequest;
import io.inhibitor.protobufwithrest.poc.service.user.GetUserResponse;

public interface UserService {

  GetUserResponse getUser(GetUserRequest request);
}
