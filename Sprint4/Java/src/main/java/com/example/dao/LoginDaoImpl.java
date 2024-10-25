package com.example.dao;

import com.example.exceptions.LoginNotCreate;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

final class LoginDaoImpl implements LoginDao{

    private Logger logger = Logger.getLogger(LoginDaoImpl.class.getName());

    @Override
    public Long insertLogin(Connection conn, Login login) throws LoginNotCreate {
        Long loginId = null;
        String sql = """ 
                    insert into T_PS_PESSOA(nm_nome, nm_email, sq_senha)
                    values (?, ?, ?)
                """;
        try(PreparedStatement ps = conn.prepareStatement(sql, new String[]{"cd_pessoa"})){
            ps.setString(1, login.getNome());
            ps.setString(2, login.getEmail());
            ps.setString(3, login.getSenha());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()){
                        loginId = rs.getLong(1);
                    }
                }
            } else {
                throw new LoginNotCreate();
            }
        } catch (SQLException e) {
            this.logger.warning("Erro ao inserir login: " + e.getMessage());
        }
        return loginId;
    }
}
