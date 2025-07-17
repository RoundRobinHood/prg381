package com.roundrobinhood.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginResponseBody {
  public boolean authorized;
  public String reason;
  public String role;
  public String auth_token;

  private static final ObjectMapper mapper = new ObjectMapper();

  public LoginResponseBody() {}

  public LoginResponseBody(boolean authorized, String reason, String role, String auth_token) {
    this.authorized = authorized;
    this.reason = reason;
    this.role = role;
    this.auth_token = auth_token;
  }

  @Override
  public String toString() {
    try {
      return mapper.writeValueAsString(this);
    } catch(JsonProcessingException ex) {
      return "JSON processing error. Object: " + super.toString();
    }
  }
  
  public static LoginResponseBody fromJSON(String json) throws JsonProcessingException {
    return mapper.readValue(json, LoginResponseBody.class);
  }
}
