package com.example.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

    Connection getConnection() throws SQLException;
}
