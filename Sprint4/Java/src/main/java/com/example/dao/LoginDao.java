package com.example.dao;

import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.LoginNotUpdade;
import com.example.model.Login;

import java.sql.Connection;

public interface LoginDao {

    Long insertLogin(Connection conn, Login login) throws LoginNotCreate;
    void updateLogin(Connection conn, Login login) throws LoginNotFound;
    Long logar(Connection conn, String email, String senha) throws LoginNotFound;
}
