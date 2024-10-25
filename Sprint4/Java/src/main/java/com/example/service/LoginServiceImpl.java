package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.LoginDao;
import com.example.dao.LoginDaoFactory;
import com.example.exceptions.LoginNotFound;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.SQLException;

final class LoginServiceImpl implements LoginService {

    private LoginDao loginDao = LoginDaoFactory.getLoginDao();

    @Override
    public Long login(Login login) throws LoginNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return loginDao.logar(connection, login);
        } catch (SQLException e) {
            throw new LoginNotFound();
        }
    }
}
