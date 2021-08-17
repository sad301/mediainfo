package com.sad301.mediainfo;

import spark.Service;

public class HttpServer {

    private final int port;

    private Service service;

    public HttpServer(int port) {
        this.port = port;
    }

    protected void start() {
        service = Service.ignite().port(port);
        service.get("/", (req, res) -> {
            return "Hello World";
        });
    }

    protected void stop() {
        service.stop();
    }
}
