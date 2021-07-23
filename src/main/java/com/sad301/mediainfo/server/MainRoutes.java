package com.sad301.mediainfo.server;

import com.sad301.mediainfo.model.Config;
import spark.Request;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public record MainRoutes(App app) {

  public Route index() {
    return (req, res) -> {
      if (tokenValid(req)) {
        res.redirect("/home");
        return "redirect";
      }
      else {
        Map<String, Object> model = new HashMap<>();
        model.put("domain", "127.0.0.1:8000");
        return app.getRenderer().render(model, "index.html.j2");
      }
    };
  }

  public Route home() {
    return (req, res) -> {
      if (tokenValid(req)) {
        return app.getRenderer().render("home.html.j2");
      }
      else {
        res.redirect("/");
        return "redirect";
      }
    };
  }

  public Route content() {
    return (req, res) -> {
      if (tokenValid(req)) {
        return app.getRenderer().render("content.html.j2");
      }
      else {
        res.redirect("/");
        return "redirect";
      }
    };
  }

  public Route config() {
    return (req, res) -> {
      if (tokenValid(req)) {
        return app.getRenderer().render("config.html.j2");
      }
      else {
        res.redirect("/");
        return "redirect";
      }
    };
  }

  public Route stop() {
    return (req, res) -> {
      app.stop();
      return "Stopped";
    };
  }

  private boolean tokenValid(Request req) throws java.sql.SQLException {
    boolean temp = false;
    Map<Config.Key, String> cfgs = app.getConfigDAO().retrieveAsMap();
    String ctoken = cfgs.getOrDefault(Config.Key.USER_TOKEN_VALUE, "");
    String token = req.cookie("Token");
    if(token != null) {
      temp = token.equals(ctoken);
    }
    return temp;
  }

}
