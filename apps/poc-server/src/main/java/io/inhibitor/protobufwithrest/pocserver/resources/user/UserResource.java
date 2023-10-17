package io.inhibitor.protobufwithrest.pocserver.resources.user;

import io.inhibitor.protobufwithrest.poc.service.user.GetUserRequest;
import io.inhibitor.protobufwithrest.poc.service.user.GetUserResponse;
import io.inhibitor.protobufwithrest.pocserver.services.user.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

  private final UserService userService;

  @Inject
  public UserResource(
      UserService userService
  ) {
    this.userService = userService;
  }

  /**
   * A non-REST endpoint supports application/json, application/x-protobuf
   * for both request and response.
   */
  @POST
  @Path("/get-user")
  @Consumes({MediaType.APPLICATION_JSON, "application/x-protobuf"})
  @Produces({MediaType.APPLICATION_JSON, "application/x-protobuf"})
  public GetUserResponse getUser(GetUserRequest request) {
    return userService.getUser(request);
  }

  /**
   * An example REST endpoint that is converted to protobuf representation.
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public GetUserResponse getUser(
      @PathParam("id") String id
  ) {
    final GetUserRequest request = GetUserRequest.newBuilder()
        .setPayload(GetUserRequest.Payload.newBuilder()
            .setId(id)
            .build())
        .build();

    return userService.getUser(request);
  }
}
