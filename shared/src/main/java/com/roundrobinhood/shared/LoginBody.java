package com.roundrobinhood.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginBody {
  public String email;
  public String password;

  private static final ObjectMapper mapper = new ObjectMapper();

  public LoginBody() {}

  public LoginBody(String email, String password) {
    this.email = email;
    this.password = password;
  }
  
  @Override
  public String toString() {
    try {
      return mapper.writeValueAsString(this);
    } catch(JsonProcessingException ex) {
      return "JSON processing error. Object: " + super.toString();
    }
  }

  public static LoginBody fromJSON(String json) throws JsonProcessingException {
    return mapper.readValue(json, LoginBody.class);
  }
}
