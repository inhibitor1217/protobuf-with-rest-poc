package io.inhibitor.protobufwithrest.pocserver.services.user;

import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.Value;
import io.inhibitor.protobufwithrest.poc.common.error.Error;
import io.inhibitor.protobufwithrest.poc.entity.user.User;
import io.inhibitor.protobufwithrest.poc.entity.user.UserType;
import io.inhibitor.protobufwithrest.poc.service.user.GetUserRequest;
import io.inhibitor.protobufwithrest.poc.service.user.GetUserResponse;
import jakarta.inject.Singleton;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
class StubUserService implements UserService {

  @Override
  public GetUserResponse getUser(GetUserRequest request) {
    final String id = request.getPayload().getId();

    if (id.isEmpty()) {
      return GetUserResponse.newBuilder()
          .setError(Error.newBuilder()
              .setStatus(400)
              .setType("validationError")
              .addErrors(Struct.newBuilder()
                  .putFields("message", Value.newBuilder()
                      .setStringValue("id is required")
                      .build())
                  .build())
              .build())
          .build();
    }

    final Instant now = Instant.now();

    return GetUserResponse.newBuilder()
        .setResult(GetUserResponse.Result.newBuilder()
            .setUser(User.newBuilder()
                .setId(request.getPayload().getId())
                .setName("사회적 거리 [두기]")
                .setType(UserType.USER_TYPE_MEMBER)
                .setProfile(Struct.newBuilder()
                    .putFields("email", Value.newBuilder()
                        .setStringValue("dugi@example.com")
                        .build())
                    .putFields("mobileNumber", Value.newBuilder()
                        .setStringValue("+821012345678")
                        .build())
                    .build())
                .setCreatedAt(Timestamp.newBuilder()
                    .setSeconds(now.getEpochSecond())
                    .setNanos(now.getNano())
                    .build())
                .setUpdatedAt(Timestamp.newBuilder()
                    .setSeconds(now.getEpochSecond())
                    .setNanos(now.getNano())
                    .build())
                .build())
            .build())
        .build();
  }
}
