package com.belgiumcampus.wellness.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private static final Properties props = new Properties();

  static {
    try {
      InputStream propStream = ResourceLoader.getResourceAsStream("config.properties");
      if (propStream == null) {
        propStream = ResourceLoader.getResourceAsStream("application.properties");
      }
      if (propStream != null) {
        props.load(propStream);
      } else {
        System.err.println("No config file found (config.properties or application.properties");
      }
    } catch (IOException e) {
      throw new ExceptionInInitializerError("Failed to load configuration: " + e.getMessage());
    }
  }

  public static String get(String key) {
    return props.getProperty(key);
  }

  public static String getOrDefault(String key, String def) {
    return props.getProperty(key, def);
  }

  public static int getInt(String key, int def) {
    String val = props.getProperty(key);
    if (val == null) return def;
    try {
      return Integer.parseInt(val);
    } catch (NumberFormatException e) {
      return def;
    }
  }

  public static boolean getBoolean(String key, boolean def) {
    String val = props.getProperty(key);
    if (val == null) return def;
    return Boolean.parseBoolean(val);
  }
}
