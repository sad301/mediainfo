package com.sad301.mediainfo.controller;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class FrontPaneController {

  private boolean loop;

  public void startTimer() {
    loop = true;
    Thread t = new Thread(() -> {
      DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id"));
      DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm:ss");
      while(loop) {
        LocalDateTime now = LocalDateTime.now();
        Platform.runLater(() -> {
          lDate.setText(now.format(fmtDate));
          lTime.setText(now.format(fmtTime));
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

  public void stopTimer() {
    loop = false;
  }

  @FXML
  private Label lDate;

  @FXML
  private Label lTime;

}
