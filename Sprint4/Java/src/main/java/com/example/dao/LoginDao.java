package com.example.dao;

import com.example.exceptions.LoginNotCreate;
import com.example.model.Login;

import java.sql.Connection;

public interface LoginDao {

    Long insertLogin(Connection conn, Login login) throws LoginNotCreate;
}
