package io.inhibitor.protobufwithrest.pocserver.libs.protobuf;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
class ProtobufJsonMessageBodyReader
    implements MessageBodyReader<Message> {

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
  )
      throws IOException, WebApplicationException {
    final Message.Builder builder;
    try {
      builder = (Message.Builder) type.getMethod("newBuilder").invoke(type);
    } catch (ReflectiveOperationException e) {
      throw new WebApplicationException(e);
    }

    JsonFormat.parser()
        .ignoringUnknownFields()
        .merge(new InputStreamReader(entityStream), builder);

    return builder.build();
  }
}
