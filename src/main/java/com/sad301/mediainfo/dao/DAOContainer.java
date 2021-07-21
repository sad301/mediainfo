package com.sad301.mediainfo.dao;

import java.sql.*;

@Deprecated
public class DAOContainer {

  private Connection connection;
  private ConfigDAO configDao;

  public DAOContainer(Connection connection) {
    this.connection = connection;
  }

  public void init() throws SQLException {
    configDao = new ConfigDAO(connection);
    configDao.init();
  }

  public void close() throws SQLException {
    configDao.close();
  }

  public ConfigDAO getConfigDAO() {
    return configDao;
  }

}
