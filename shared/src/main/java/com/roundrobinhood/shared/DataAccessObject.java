package com.roundrobinhood.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataAccessObject {
  private static final ObjectMapper mapper = new ObjectMapper();
  @Override
  public String toString() {
    try {
      return mapper.writeValueAsString(this);
    } catch(JsonProcessingException ex) {
      return "JSON processing error. Object: " + super.toString();
    }
  }

  public static <T> T fromJSON(String json, Class<T> clazz) throws JsonProcessingException {
    return mapper.readValue(json, clazz);
  }

  public DataAccessObject() {}
}
