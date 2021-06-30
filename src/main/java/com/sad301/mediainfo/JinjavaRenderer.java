package com.sad301.mediainfo;

import com.hubspot.jinjava.*;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import org.apache.commons.io.*;

public class JinjavaRenderer {

  public static String defaultRender(String filename) {
    Map<String, Object> model = new HashMap<>();
    return defaultRender(model, filename);
  }

  public static String defaultRender(Map<String, Object> model, String filename) {
    JinjavaRenderer r = new JinjavaRenderer(JinjavaRenderer.EXTERNAL_RESOURCE, "templates");
    String html = null;
    try {
      html = r.render(model, filename);
    }
    catch(IOException exc) {
      html = exc.toString();
    }
    return html;
  }

  public static final int EXTERNAL_RESOURCE = 0;
  public static final int CLASSPATH_RESOURCE = 1;

  private final int resource;
  private final String basedir;

  private Jinjava jinjava;

  public JinjavaRenderer(int resource, String basedir) {
    this.resource = resource;
    this.basedir = basedir;
    init();
  }

  private void init() {
    jinjava = new Jinjava();
    jinjava.setResourceLocator((s, c, i) -> {
      String temp = null;
      switch(resource) {
        case EXTERNAL_RESOURCE -> {
          FileInputStream in = new FileInputStream(String.format("%s/%s", basedir, s));
          temp = IOUtils.toString(in, c);
        }
        case CLASSPATH_RESOURCE -> {
          URL url = getClass().getResource(String.format("/%s/%s", basedir, s));
          temp = IOUtils.toString(url, c);
        }
      }
      return temp;
    });
  }

  public String render(String filename) throws IOException {
    return render(new HashMap<>(), filename);
  }

  public String render(Map<String, Object> model, String filename) throws IOException {
    String template = null;
    switch(resource) {
      case EXTERNAL_RESOURCE -> {
        FileInputStream in = new FileInputStream(String.format("%s/%s", basedir, filename));
        template = IOUtils.toString(in, StandardCharsets.UTF_8);
      }
      case CLASSPATH_RESOURCE -> {
        URL url = getClass().getResource(String.format("/%s/%s", basedir, filename));
        template = IOUtils.toString(url, StandardCharsets.UTF_8);
      }
    }
    return jinjava.render(template, model);
  }

}
