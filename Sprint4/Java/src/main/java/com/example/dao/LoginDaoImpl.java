package com.example.dao;

import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.LoginNotUpdade;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

final class LoginDaoImpl implements LoginDao{

    private Logger logger = Logger.getLogger(LoginDaoImpl.class.getName());

    @Override
    public void insertLogin(Connection conn, Login login) throws LoginNotCreate {
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
                        login.setId(rs.getLong(1));
                    }
                }
            } else {
                throw new LoginNotCreate();
            }
        } catch (SQLException e) {
            this.logger.warning("Erro ao inserir login: " + e.getMessage());
        }
    }

    @Override
    public void updateLogin(Connection conn, Login login) throws LoginNotFound {
        String sql = """
                update T_PS_PESSOA
                set nm_nome = ?, sq_senha = ?
                where cd_pessoa = ?
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login.getNome());
            ps.setString(2, login.getSenha());
            ps.setLong(3, login.getId());
            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new LoginNotFound();
            }
        } catch (SQLException e) {
            this.logger.warning("Erro ao atualizar login: " + e.getMessage());
        }
    }

    @Override
    public Long logar(Connection conn, String email, String senha) throws LoginNotFound {
        String sql = """
                        select cd_pessoa from T_PS_PESSOA where nm_email = ? and sq_senha = ?
                    """;
        Long loginId = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    loginId = rs.getLong(1);
                } else {
                    throw new LoginNotFound();
                }
            }
        } catch (SQLException e) {
            this.logger.warning("Erro ao ralizar login: " + e.getMessage());
        }
        return loginId;
    }
}
