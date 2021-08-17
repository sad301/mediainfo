package com.sad301.mediainfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    protected Connection connection;

    public DAO() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            connection = DriverManager.getConnection("jdbc:sqlite:database/mediainfo.db");
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public abstract void create(T t) throws SQLException;
    public abstract List<T> retrieve() throws SQLException;
    public abstract void update(T t) throws SQLException;
    public abstract void delete(T t) throws SQLException;
}
