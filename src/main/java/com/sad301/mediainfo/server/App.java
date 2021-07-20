package com.sad301.mediainfo.server;

import com.sad301.mediainfo.dao.ConfigDAO;
import spark.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class App {

  private final int port;
  private Renderer renderer;
  private Service service;
  private Connection connection;
  private ConfigDAO configDAO;
  private ApiRoutes apiRoutes;

  public App(int port) {
    this.port = port;
    initRoutes();
    initDatabase();
    initRenderer();
  }

  private void initRoutes() {
    apiRoutes = new ApiRoutes(this);
  }

  private void initDatabase() {
    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());
      connection = DriverManager.getConnection("jdbc:sqlite:database/mediainfo.db");
      configDAO = new ConfigDAO(connection);
      configDAO.init();
    }
    catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  private void initRenderer() {
    renderer = new Renderer(Renderer.EXTERNAL_RESOURCE, "templates");
  }

  public void start() {
    service = Service.ignite().externalStaticFileLocation("statics").port(port);
    service.get("/", (req, res) -> {
      String token = req.cookie("Token");
      System.out.println(token);
      Map<String, Object> model = new HashMap<>();
      model.put("domain", "127.0.0.1:8000");
      return renderer.render(model, "index.html.j2");
    });
    service.get("/home", (req, res) -> renderer.render("home.html.j2"));
    service.get("/stop", (req, res) -> {
      stop();
      return "Stopped";
    });
    service.get("/api", apiRoutes.index());
    service.get("/api/configs", apiRoutes.configs());
    service.post("/api/login", new LoginHandler(this));
  }

  public void stop() throws Exception {
    configDAO.stop();
    service.stop();
  }

  protected ConfigDAO getConfigDAO() {
    return configDAO;
  }

  public static void main(String[] args) {
    App app = new App(8000);
    app.start();
  }

}
