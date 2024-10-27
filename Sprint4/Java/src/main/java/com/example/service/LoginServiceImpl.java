package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.LoginDao;
import com.example.dao.LoginDaoFactory;
import com.example.exceptions.ErroLogar;
import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.LoginNotUpdade;
import com.example.model.Login;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

final class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao = LoginDaoFactory.getLoginDao();

    @Override
    public Long login(String email, String senha) throws LoginNotFound, ErroLogar {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            Map<Long, String> dados = loginDao.logar(connection, email);
            for(Map.Entry<Long, String> entry : dados.entrySet()) {
                if(BCrypt.checkpw(senha, entry.getValue())) {
                    return entry.getKey();
                }
            }
        } catch (SQLException e) {
            throw new ErroLogar();
        }
        throw new ErroLogar();
    }

    @Override
    public void cadastrar(Login login) throws LoginNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            login.setSenha(BCrypt.hashpw(login.getSenha(), BCrypt.gensalt()));
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
