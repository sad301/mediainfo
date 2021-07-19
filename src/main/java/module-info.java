module com.sad301.mediainfo {
  requires java.sql;
  requires javafx.controls;
  requires javafx.fxml;
  requires spark.core;
  requires jinjava;
  requires org.apache.commons.io;
  exports com.sad301.mediainfo;
  opens com.sad301.mediainfo.controller to javafx.fxml;
}
