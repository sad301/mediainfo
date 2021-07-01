package com.sad301.mediainfo.gui;

import java.util.*;
import java.time.*;
import java.time.format.*;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class FooterController implements Runnable {

  @FXML
  private Label lTime;

  @FXML
  private Label lDate;

  @FXML
  public void initialize() {
    Thread t = new Thread(this);
    t.start();
  }

  private boolean loop;
  private DateTimeFormatter fmtTime;
  private DateTimeFormatter fmtDate;

  @Override
  public void run() {
    loop = true;
    fmtTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("id", "ID"));
    while(loop) {
      LocalDateTime now = LocalDateTime.now();
      Platform.runLater(() -> {
        lTime.setText(now.format(fmtTime));
        lDate.setText(now.format(fmtDate));
      });
      try {
        Thread.sleep(1000);
      }
      catch(InterruptedException exc) { } // ignored
    }
  }

  public void stop() {
    loop = false;
  }

}
