package com.sad301.mediainfo.dao;

import com.sad301.mediainfo.model.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigDAO implements DAO<Config> {

  public static Map<Config.Key, String> retrieveAsMap2() {
    Map<Config.Key, String> map = new HashMap<>();
    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());
      Connection c = DriverManager.getConnection("jdbc:sqlite:database/mediainfo.db");
      ConfigDAO dao = new ConfigDAO(c);
      dao.init();
      map = dao.retrieveAsMap();
      dao.close();
      c.close();
    }
    catch(SQLException exc) {
      exc.printStackTrace();
    }
    return map;
  }

  private final Connection connection;
  private PreparedStatement psCreate, psRetrieve, psUpdate, psDelete;

  public ConfigDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public int create(Config config) throws SQLException {
    psCreate.setString(1, config.getKey().toString());
    psCreate.setString(2, config.getValue());
    psCreate.setString(3, config.getValue());
    return psCreate.executeUpdate();
  }

  @Override
  public List<Config> retrieve() throws SQLException {
    List<Config> temp = new ArrayList<>();
    ResultSet rs = psRetrieve.executeQuery();
    while (rs.next()) {
      String key = rs.getString("app_key");
      String value = rs.getString("app_value");
      temp.add(Config.init().key(Config.Key.get(key)).value(value));
    }
    rs.close();
    return temp;
  }

  public Map<Config.Key, String> retrieveAsMap() throws SQLException {
    Map<Config.Key, String> temp = new HashMap<>();
    retrieve().forEach(c -> temp.put(c.getKey(), c.getValue()));
    return temp;
  }

  @Override
  public int update(Config config) throws SQLException {
    psUpdate.setString(1, config.getValue());
    psUpdate.setString(2, config.getKey().toString());
    return psUpdate.executeUpdate();
  }

  @Override
  public int delete(Config config) throws SQLException {
    psDelete.setString(1, config.getKey().toString());
    return psDelete.executeUpdate();
  }

  @Override
  public void init() throws SQLException {
    psCreate = connection.prepareStatement("insert into config (app_key, app_value) values (?, ?) on conflict (app_key) do update set app_value=?");
    psRetrieve = connection.prepareStatement("select * from config");
    psUpdate = connection.prepareStatement("update config set app_value=? where app_key=?");
    psDelete = connection.prepareStatement("delete from config where app_key=?");
  }

  @Override
  public void close() throws SQLException {
    psCreate.close();
    psRetrieve.close();
    psUpdate.close();
    psDelete.close();
  }
}
