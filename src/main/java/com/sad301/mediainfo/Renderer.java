package com.sad301.mediainfo;

import com.hubspot.jinjava.Jinjava;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Renderer {

    private final String basedir;
    private Jinjava jinjava;

    public Renderer(String basedir) {
        this.basedir = basedir;
        init();
    }

    private void init() {
        jinjava = new Jinjava();
        jinjava.setResourceLocator((s, c, i) -> {
            URL url = getClass().getResource(String.format("%s/%s", basedir, s));
            return IOUtils.toString(url, c);
        });
    }

    public String render(Map<String, Object> model, String filename) throws IOException {
        URL url = getClass().getResource(String.format("%s/%s", basedir, filename));
        String template = IOUtils.toString(url, StandardCharsets.UTF_8);
        return jinjava.render(template, model);
    }
}
