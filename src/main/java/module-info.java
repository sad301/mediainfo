module com.sad301.mediainfo {
  requires java.sql;
  requires javafx.controls;
  requires javafx.fxml;
  requires spark.core;
  requires jinjava;
  requires org.json;
  requires org.apache.commons.io;
  requires org.xerial.sqlitejdbc;
  exports com.sad301.mediainfo;
  opens com.sad301.mediainfo.controller to javafx.fxml;
}
