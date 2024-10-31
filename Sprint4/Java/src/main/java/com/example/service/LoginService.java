package com.example.service;

import com.example.exceptions.*;
import com.example.model.Login;

public interface LoginService {

    Long login(String email, String senha) throws LoginNotFound, ErroLogar;

    void cadastrar(Login login) throws LoginNotCreate, CadastroInvalido;

    void update(Login login) throws LoginNotFound, LoginNotUpdade;
}
