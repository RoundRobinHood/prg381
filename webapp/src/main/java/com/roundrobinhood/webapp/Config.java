package com.roundrobinhood.webapp;

public class Config {
  private static String connectionStr;
  public static String getConnectionStr() {
    if(connectionStr == null) {
      connectionStr = System.getenv("CONN_STR");
    }
    return connectionStr;
  }
  private static String dbUser;
  public static String getDbUser() {
    if(dbUser == null) {
      dbUser = System.getenv("DB_USER");
    }
    return dbUser;
  }
  private static String dbPass;
  public static String getDbPass() {
    if(dbPass == null) {
      dbPass = System.getenv("DB_PASS");
    }
    return dbPass;
  }
  private static Boolean httpSecure;
  public static boolean getHTTPSecure() {
    if(httpSecure == null) {
      httpSecure = System.getenv("HTTP_SECURE") != null && System.getenv("HTTP_SECURE").equalsIgnoreCase("true");
    }

    return httpSecure.booleanValue();
  }
}
