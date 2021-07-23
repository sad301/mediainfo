package com.sad301.mediainfo.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FooterPaneController {

  private boolean loop = true;

  public void start() {
    Thread t = new Thread(() -> {
      DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM", new Locale("id"));
      DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm");
      while(loop) {
        LocalDateTime now = LocalDateTime.now();
        Platform.runLater(() -> {
          txtDate.setText(now.format(fmtDate));
          txtTime.setText(now.format(fmtTime));
        });
        try {
          Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
          exc.printStackTrace();
        }
      }
    });
    t.start();
  }

  public void stop() {
    loop = false;
  }

  @FXML
  private Text txtDate;

  @FXML
  private Text txtTime;

  @FXML
  public void initialize() { }

}
