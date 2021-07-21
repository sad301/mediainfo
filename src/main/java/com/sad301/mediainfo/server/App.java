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
  private MainRoutes mainRoutes;

  public App(int port) {
    this.port = port;
    initRoutes();
    initDatabase();
    initRenderer();
  }

  private void initRoutes() {
    mainRoutes = new MainRoutes(this);
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
    // --- Main Routes ---
    service.get("/", mainRoutes.index());
    service.get("/home", mainRoutes.home());
    service.get("/content", mainRoutes.content());
    service.get("/config", mainRoutes.config());
    service.get("/stop", mainRoutes.stop());
    // --- API Routes ---
    service.get("/api", apiRoutes.index());
    service.get("/api/configs", apiRoutes.configs());
    service.get("/api/configs/keys", apiRoutes.configKeys());
    service.post("/api/login", new LoginHandler(this));
  }

  public void stop() throws Exception {
    configDAO.close();
    service.stop();
  }

  protected Renderer getRenderer() {
    return renderer;
  }

  protected ConfigDAO getConfigDAO() {
    return configDAO;
  }

  public static void main(String[] args) {
    App app = new App(8000);
    app.start();
  }

}
