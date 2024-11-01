package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.LoginDao;
import com.example.dao.LoginDaoFactory;
import com.example.exceptions.*;
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
            if(dados == null){
                throw new LoginNotFound();
            }
            Long id = null;
            for(Map.Entry<Long, String> entry : dados.entrySet()) {
                if(BCrypt.checkpw(senha, entry.getValue())) {
                    id = entry.getKey();
                }
            }
            if(id == null) {
                throw new LoginNotFound();
            }
            return id;
        } catch (SQLException e) {
            throw new ErroLogar(e.getMessage());
        }
    }
    @Override
    public void cadastrar(Connection connection, Login login) throws LoginNotCreate, CadastroInvalido, SQLException {
        try {
            try {
                loginDao.logar(connection, login.getEmail());
                throw new CadastroInvalido("Ja existe usuario com esse email");
            } catch (LoginNotFound e) {
                login.setSenha(BCrypt.hashpw(login.getSenha(), BCrypt.gensalt()));
                loginDao.insertLogin(connection, login);
            }
        } catch (SQLException e) {
            throw new LoginNotCreate(e.getMessage());
        }
    }

    @Override
    public void update(Connection connection, Login login) throws LoginNotFound, LoginNotUpdade, SQLException {
        try {
            login.setSenha(BCrypt.hashpw(login.getSenha(), BCrypt.gensalt()));
            loginDao.updateLogin(connection, login);
        } catch (SQLException e) {
            throw new LoginNotUpdade(e.getMessage());
        }
    }
}
