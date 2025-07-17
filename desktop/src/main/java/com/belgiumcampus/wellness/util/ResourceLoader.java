package com.belgiumcampus.wellness.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {
  public static InputStream getResourceAsStream(String name) throws FileNotFoundException {
    // Try classloader first
    InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(name);
    if (stream != null) return stream;

    // Try file system fallback (relative path)
    File file = new File(name);
    if (file.exists() && file.isFile()) {
      return new FileInputStream(file);
    }
    return null;
  }
}
