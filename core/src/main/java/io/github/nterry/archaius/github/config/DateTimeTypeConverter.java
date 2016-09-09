package io.github.nterry.archaius.github.config;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.*;
import org.joda.time.DateTime;

/**
 * Responsible for deserializing a {@link DateTime} in {@link Gson}.
 *
 * @author Nicholas Terry
 */
class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

  @Override
  public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
    return new JsonPrimitive(src.toString());
  }

  @Override
  public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    try {
      return new DateTime(json.getAsString());
    }
    catch (IllegalArgumentException e) {
      // May be it came in formatted as a java.util.Date, so try that
      Date date = context.deserialize(json, Date.class);
      return new DateTime(date);
    }
  }
}
