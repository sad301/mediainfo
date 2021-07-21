package com.sad301.mediainfo.controller;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.text.*;

public class FooterPaneController {

  private boolean loop = true;

  public void start() {
    Thread t = new Thread(() -> {
      DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id"));
      DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm:ss");
      while(loop) {
        LocalDateTime now = LocalDateTime.now();
        Platform.runLater(() -> {
          txtDate.setText(now.format(fmtDate));
          txtTime.setText(now.format(fmtTime));
        });
        try {
          Thread.sleep(500);
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
