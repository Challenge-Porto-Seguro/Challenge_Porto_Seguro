package com.example.service;

import com.example.exceptions.LoginNotFound;
import com.example.model.Login;

public interface LoginService {

    Long login(Login login) throws LoginNotFound;
}
