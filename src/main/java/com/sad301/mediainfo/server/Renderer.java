package com.sad301.mediainfo.server;

import com.hubspot.jinjava.*;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import org.apache.commons.io.*;

public class Renderer {

  public static String renderExternal(String basedir, String filename) {
    return renderExternal(basedir, new HashMap<>(), filename);
  }

  public static String renderExternal(String basedir, Map<String, Object> model, String filename) {
    Renderer r = new Renderer(Renderer.EXTERNAL_RESOURCE, basedir);
    String html;
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

  public Renderer(int resource, String basedir) {
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
