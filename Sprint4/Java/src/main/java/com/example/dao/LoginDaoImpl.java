package com.example.dao;

import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.LoginNotFound;
import com.example.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
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
    public Map<Long, String> logar(Connection conn, String email) throws LoginNotFound {
        String sql = """
                        select cd_pessoa, sq_senha from T_PS_PESSOA where nm_email = ?
                    """;
        Long loginId = null;
        String senha = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    loginId = rs.getLong(1);
                    senha = rs.getString(2);
                    return Map.of(loginId, senha);
                } else {
                    throw new LoginNotFound();
                }
            }
        } catch (SQLException e) {
            this.logger.warning("Erro ao ralizar login: " + e.getMessage());
        }
        return null;
    }
}
