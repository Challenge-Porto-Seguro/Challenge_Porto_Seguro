package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.LoginDao;
import com.example.dao.LoginDaoFactory;
import com.example.exceptions.ErroLogar;
import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.LoginNotUpdade;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.SQLException;

final class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao = LoginDaoFactory.getLoginDao();

    @Override
    public Long login(String email, String senha) throws LoginNotFound, ErroLogar {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return loginDao.logar(connection, email, senha);
        } catch (SQLException e) {
            throw new ErroLogar();
        }
    }

    @Override
    public void cadastrar(Login login) throws LoginNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            loginDao.insertLogin(connection, login);
            connection.commit();
        } catch (SQLException e) {
            throw new LoginNotCreate();
        }
    }

    @Override
    public void update(Login login) throws LoginNotFound, LoginNotUpdade {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            loginDao.updateLogin(connection, login);
        } catch (SQLException e) {
            throw new LoginNotUpdade();
        }
    }
}
