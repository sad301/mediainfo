package com.sad301.mediainfo.server;

import com.sad301.mediainfo.model.Config;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

public class LoginHandler implements Route {

  private final App app;

  public LoginHandler(App app) {
    this.app = app;
  }

  @Override
  public Object handle(Request req, Response res) throws Exception {
    String body = req.body();
    if (body.equals("")) {
      res.status(400);
      return new JSONObject().put("message", "invalid request").toString();
    }
    JSONObject payload = new JSONObject(body);
    String data = payload.getString("data");
    String hash = payload.getString("hash");
    byte[] jsAuth = Base64.getDecoder().decode(data);
    JSONObject auth = new JSONObject(new String(jsAuth));
    Map<Config.Key, String> map = app.getConfigDAO().retrieveAsMap();
    String username = map.get(Config.Key.USER_USERNAME);
    String password = map.get(Config.Key.USER_PASSWORD);
    if (!auth.get("username").equals(username)) {
      res.status(403);
      return new JSONObject().put("message", "forbidden").toString();
    }
    if (!Utils.authValid(jsAuth, hash, password)) {
      res.status(403);
      return new JSONObject().put("message", "forbidden").toString();
    }
    String token = Utils.token();
    app.getConfigDAO().create(new Config(Config.Key.USER_TOKEN_VALUE, token));
    app.getConfigDAO().create(new Config(Config.Key.USER_TOKEN_CREATED, LocalDateTime.now().toString()));
    return new JSONObject().put("message", "login valid").put("token", token).toString();
  }
}
