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

}
