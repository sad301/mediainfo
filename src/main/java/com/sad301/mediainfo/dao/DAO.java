package com.sad301.mediainfo.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    int create(T t) throws SQLException;
    List<T> retrieve() throws SQLException;
    int update(T t) throws SQLException;
    int delete(T t) throws SQLException;
    void init() throws SQLException;
    void stop() throws SQLException;
}
