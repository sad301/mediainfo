package com.sad301.mediainfo.dao;

import java.sql.*;

public class DatabaseConnector {

  public static Connection getConnection() throws SQLException {
    DriverManager.registerDriver(new org.sqlite.JDBC());
    return DriverManager.getConnection("jdbc:sqlite:database/mediainfo.db");
  }

}
