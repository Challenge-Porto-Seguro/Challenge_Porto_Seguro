package com.example.dao;

import com.example.exceptions.LoginNotFound;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface LoginDao {

    void insertLogin(Connection conn, Login login) throws SQLException;
    void updateLogin(Connection conn, Login login) throws LoginNotFound, SQLException;
    Map<Long, String> logar(Connection conn, String email) throws LoginNotFound, SQLException;
}
