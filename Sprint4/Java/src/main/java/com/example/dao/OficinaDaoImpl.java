package com.example.dao;

import com.example.exceptions.OficinaNotFound;
import com.example.model.Oficina;
import com.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OficinaDaoImpl implements OficinaDao {

    @Override
    public void insertOficina(Connection conn, Oficina oficina) throws SQLException {
        String sql = """
                insert into T_PS_OFICINA(cd_oficina, sq_cnpj, sq_inscricao_estadual)
                values (?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, oficina.getId());
            ps.setString(2, oficina.getCnpj());
            ps.setString(3, oficina.getInscricaoEstadual());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateOficina(Connection conn, Oficina oficina) throws OficinaNotFound, SQLException {
        String sql = """
                update T_PS_OFICINA
                set sq_cnpj = ?,
                sq_inscricao_estadual = ?
                where cd_pessoa = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, oficina.getCnpj());
            ps.setString(2, oficina.getInscricaoEstadual());
            ps.setLong(3, oficina.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new OficinaNotFound();
            }
        }
    }

    @Override
    public void deleteById(Connection conn, Long id) throws OficinaNotFound, SQLException {
        String sql = """
            delete from T_PS_OFICINA where cd_pessoa = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new OficinaNotFound();
            }
        }
    }

    @Override
    public Optional<Oficina> findById(Connection conn, Long id) throws SQLException {
        String sql = """
            select oficina.sq_cnpj, oficina.sq_inscricao_estadual, login.* from T_PS_OFICINA oficina
            join T_PS_PESSOA login on oficina.cd_oficina = login.cd_pessoa
            where oficina.cd_oficina = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(instaciaOficina(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Oficina> findAll(Connection conn) throws SQLException {
        List<Oficina> oficinas = new ArrayList<>();
        String sql = """
             select oficina.sq_cnpj, oficina.sq_inscricao_estadual, login.* from T_PS_OFICINA oficina
            join T_PS_PESSOA login on oficina.cd_oficina = login.cd_pessoa
            order by nm_nome
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                oficinas.add(instaciaOficina(rs));
            }
        }
        return oficinas;
    }

    private Oficina instaciaOficina(ResultSet rs) throws SQLException {
        Oficina oficina = new Oficina(rs.getString("nm_nome"), rs.getString("sq_cnpj"), rs.getString("nm_email"), rs.getString("sq_senha"), rs.getString("sq_inscricao_estadual"));
        oficina.setId(rs.getLong("cd_pessoa"));
        return oficina;
    }
}
