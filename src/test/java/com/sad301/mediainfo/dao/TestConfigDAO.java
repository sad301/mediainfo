package com.sad301.mediainfo.dao;

import com.sad301.mediainfo.model.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestConfigDAO {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection("jdbc:sqlite:database/mediainfo.db");
    }

    public void testRetrieve() throws SQLException {
        ConfigDAO dao = new ConfigDAO(getConnection());
        dao.init();
        List<Config> rows = dao.retrieve();
        for (Config row: rows) {
            System.out.println(row.getKey());
            System.out.println(row.getValue());
        }
        dao.stop();
    }

    public void testRetrieveAsMap() throws SQLException {
        ConfigDAO dao = new ConfigDAO(getConnection());
        dao.init();
        Map<Config.Key, String> map = dao.retrieveAsMap();
        map.keySet().forEach(k -> {
            System.out.println(k.toString());
            System.out.println(map.get(k));
        });
        dao.stop();
    }
}
