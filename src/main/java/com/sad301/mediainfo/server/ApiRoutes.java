package com.sad301.mediainfo.server;

import com.sad301.mediainfo.model.*;

import java.util.*;
import org.json.*;
import spark.*;

public class ApiRoutes {

  private App app;

  public ApiRoutes(App app) {
    this.app = app;
  }

  public Route index() {
    return (req, res) -> {
      res.header("Content-Type", "application/json");
      return new JSONObject().put("message", "api index").toString();
    };
  }

  public Route configs() {
    return (req, res) -> {
      JSONArray array = new JSONArray();
      List<Config> cfgs = app.getConfigDAO().retrieve();
      for(Config cfg: cfgs) {
        JSONObject obj = new JSONObject();
        obj.put(cfg.getKey().toString(), cfg.getValue());
        array.put(obj);
      }
      res.header("Content-Type", "application/json");
      return array.toString(4);
    };
  }

  public Route configKeys() {
    return (req, res) -> {
      Config.Key[] keys = Config.Key.values();
      JSONArray array = new JSONArray();
      for (Config.Key key: keys) {
        array.put(key.toString());
      }
      res.header("Content-Type", "application/json");
      return array.toString(4);
    };
  }

  public Route logout() {
    return (req, res) -> {
      app.getConfigDAO().delete(new Config(Config.Key.USER_TOKEN_VALUE));
      app.getConfigDAO().delete(new Config(Config.Key.USER_TOKEN_CREATED));
      res.header("Set-Cookie", "Token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 GMT");
      return new JSONObject().put("message", "logged out").toString();
    };
  }

}
