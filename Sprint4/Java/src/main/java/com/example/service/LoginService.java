package com.example.service;

import com.example.exceptions.ErroLogar;
import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.LoginNotUpdade;
import com.example.model.Login;

public interface LoginService {

    Long login(String email, String senha) throws LoginNotFound, ErroLogar;

    void cadastrar(Login login) throws LoginNotCreate;

    void update(Login login) throws LoginNotFound, LoginNotUpdade;
}
