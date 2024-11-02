package com.example.service;

import com.example.exceptions.*;
import com.example.model.Login;

import java.sql.Connection;

public interface LoginService {

    Long login(String email, String senha) throws LoginNotFound, ErroLogar;

    void cadastrar(Connection connection, Login login) throws LoginNotCreate, CadastroInvalido;

    void update(Connection connection, Login login) throws LoginNotFound, LoginNotUpdade;
}
