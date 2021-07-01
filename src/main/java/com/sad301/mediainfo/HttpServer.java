package com.sad301.mediainfo;

import java.io.*;
import java.util.*;
import spark.*;

public class HttpServer {

  private Service service;
  private final int port;

  public HttpServer(int port) {
    this.port = port;
  }

  public void start() {
    service = Service.ignite().port(port);
    service.get("/", (req, res) -> "Nothing to see here");
    service.get("/admin", (req, res) -> JinjavaRenderer.defaultRender("index.html.j2"));
    service.get("/admin/home", (req, res) -> JinjavaRenderer.defaultRender("home.html.j2"));
    service.get("/admin/user", (req, res) -> JinjavaRenderer.defaultRender("user.html.j2"));
  }

  public void stop() {
    service.stop();
  }

}
