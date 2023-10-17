package io.inhibitor.protobufwithrest.pocserver.libs.protobuf;

import com.google.protobuf.Message;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes("application/x-protobuf")
class ProtobufMessageBodyReader implements MessageBodyReader<Message> {

  @Override
  public boolean isReadable(
      Class<?> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType
  ) {
    return Message.class.isAssignableFrom(type);
  }

  @Override
  public Message readFrom(
      Class<Message> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream
  ) throws IOException, WebApplicationException {
    final Message.Builder builder;
    try {
      builder = (Message.Builder) type.getMethod("newBuilder").invoke(type);
    } catch (ReflectiveOperationException e) {
      throw new WebApplicationException(e);
    }
    return builder.mergeFrom(entityStream).build();
  }
}
