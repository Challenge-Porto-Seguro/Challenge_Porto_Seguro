package com.example.dao;

import com.example.exceptions.AutomovelNotCreate;
import com.example.exceptions.AutomovelNotFound;
import com.example.model.Automovel;
import com.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class AutomovelDaoImpl implements AutomovelDao {

    @Override
    public void insert(Connection conn, Automovel automovel) throws SQLException {
        String sql = """
            insert into T_PS_AUTOMOVEL (cd_pessoa, nm_marca_veiculo, nm_modelo_veiculo, sq_placa, dt_veiculo)
            values (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] {"cd_automovel"})) {
            ps.setLong(1, automovel.getUsuario().getId());
            ps.setString(2, automovel.getMarca());
            ps.setString(3, automovel.getModelo());
            ps.setString(4, automovel.getPlaca());

            ps.setDate(5, Date.valueOf(automovel.getAno()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        Long id = rs.getLong(1);
                        automovel.setId(id);
                    }
                }
            }
        }
    }


    @Override
    public void update(Connection conn, Automovel automovel) throws AutomovelNotFound, SQLException {
        String sql = """
        update T_PS_AUTOMOVEL
        set nm_marca_veiculo = ?, nm_modelo_veiculo = ?, sq_placa = ?, dt_veiculo = ?
        where cd_automovel = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, automovel.getMarca());
            ps.setString(2, automovel.getModelo());
            ps.setString(3, automovel.getPlaca());
            ps.setDate(4, Date.valueOf(automovel.getAno()));
            ps.setLong(5, automovel.getId());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new AutomovelNotFound();
            }
        }
    }


    @Override
    public void deleteById(Connection conn, long id) throws AutomovelNotFound, SQLException {
        String sql = """
            delete from T_PS_AUTOMOVEL where cd_automovel = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new AutomovelNotFound();
            }
        }
    }


    @Override
    public Optional<Automovel> findById(Connection conn, long id) throws SQLException {
        String sql = """
            select aut.*, usuario.sq_cpf, pessoa.* 
            from T_PS_AUTOMOVEL aut
            join T_PS_USUARIO usuario on aut.cd_pessoa = usuario.cd_pessoa
            join T_PS_PESSOA pessoa on usuario.cd_pessoa = pessoa.cd_pessoa
            where aut.cd_automovel = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Automovel automovel = instanciaAutomovel(rs);
                    return Optional.of(automovel);
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Automovel> findAll(Connection conn, Long idUsuario) throws SQLException {
        List<Automovel> automoveis = new ArrayList<>();
        String sql = """ 
            select aut.*, usuario.sq_cpf, pessoa.* 
            from T_PS_AUTOMOVEL aut
            join T_PS_USUARIO usuario on aut.cd_pessoa = usuario.cd_pessoa
            join T_PS_PESSOA pessoa on usuario.cd_pessoa = pessoa.cd_pessoa
            where aut.cd_pessoa = ?
        """;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idUsuario);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    automoveis.add(instanciaAutomovel(rs));
                }
            }
        }
        return automoveis;
    }

    private Automovel instanciaAutomovel(ResultSet rs) throws SQLException {
        Automovel automovel = new Automovel(rs.getString("nm_marca_veiculo"), rs.getString("nm_modelo_veiculo"), rs.getString("sq_placa"), rs.getDate("dt_veiculo").toLocalDate(), instaciaUsuario(rs));
        automovel.setId(rs.getLong("cd_automovel"));
        return automovel;
    }

    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }
}
