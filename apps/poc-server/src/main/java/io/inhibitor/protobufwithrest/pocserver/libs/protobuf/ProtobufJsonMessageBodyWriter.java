package io.inhibitor.protobufwithrest.pocserver.libs.protobuf;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
class ProtobufJsonMessageBodyWriter
    implements MessageBodyWriter<Message> {

  @Override
  public boolean isWriteable(
      Class<?> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType
  ) {
    return Message.class.isAssignableFrom(type);
  }

  @Override
  public void writeTo(
      Message message,
      Class<?> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      MultivaluedMap<String, Object> httpHeaders,
      OutputStream entityStream
  ) throws IOException, WebApplicationException {
    entityStream.write(JsonFormat.printer().print(message).getBytes());
  }
}
