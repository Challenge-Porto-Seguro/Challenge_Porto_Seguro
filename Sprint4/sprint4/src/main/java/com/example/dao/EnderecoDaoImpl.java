package com.example.dao;

import com.example.exceptions.EnderecoNotFound;
import com.example.model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

final class EnderecoDaoImpl implements EnderecoDao {

    @Override
    public void insertEndereco(Connection conn, Endereco endereco) throws SQLException {
        String sql = """
            insert into T_PS_ENDERECO (cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa)
            values (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"cd_endereco"})) {
            ps.setLong(1, endereco.getIdUsuario());
            ps.setString(2, endereco.getRua());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getBairro());
            ps.setString(6, endereco.getCep());
            ps.setInt(7, endereco.getNumero());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    endereco.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void updateEndereco(Connection conn, Endereco endereco) throws EnderecoNotFound, SQLException {
        String sql = """
            update T_PS_ENDERECO set nm_rua = ?, nm_cidade = ?, nm_estado = ?, nm_bairro = ?, sq_cep = ?, nr_casa = ?
            where cd_pessoa = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getUf());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCep());
            ps.setInt(6, endereco.getNumero());
            ps.setLong(7, endereco.getIdUsuario());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new EnderecoNotFound();
            }
        }
    }

    @Override
    public void deleteByCdPessoa(Connection conn, Long idPessoa) throws EnderecoNotFound, SQLException {
        String sql = """
        delete from T_PS_ENDERECO where cd_pessoa = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idPessoa);
            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new EnderecoNotFound();
            }
        }
    }

    @Override
    public Optional<Endereco> findById(Connection conn, Long id) throws SQLException {
        String sql = """
        select cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa
        from T_PS_ENDERECO where cd_pessoa = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = InstanciaObjetos.instanciaEndereco(rs);
                    return Optional.of(endereco);
                }
            }
        }
        return Optional.empty();
    }
}
