package com.sad301.mediainfo.server;

import java.sql.*;
import java.util.*;
import spark.*;

public class App {

  private final int port;
  private Renderer renderer;
  private Service service;

  public App(int port) {
    this.port = port;
    initRenderer();
  }

  private void initRenderer() {
    renderer = new Renderer(Renderer.EXTERNAL_RESOURCE, "templates");
  }

  public void start() {
    service = Service.ignite().externalStaticFileLocation("statics").port(port);
    service.get("/", (req, res) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("domain", "127.0.0.1:8000");
      return renderer.render(model, "index.html.j2");
    });
    service.get("/home", (req, res) -> renderer.render("home.html.j2"));
    service.get("/stop", (req, res) -> {
      stop();
      return "Stopped";
    });
    service.get("/api", (req, res) -> {
      res.header("Content-Type", "application/json");
      return "{\"message\":\"hello world\"}";
    });
  }

  public void stop() {
    service.stop();
  }

  public static void main(String[] args) {
    App app = new App(8000);
    app.start();
  }

}
