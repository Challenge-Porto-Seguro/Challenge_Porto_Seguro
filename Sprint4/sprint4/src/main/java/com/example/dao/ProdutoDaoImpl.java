package com.example.dao;

import com.example.model.Produto;

import java.sql.*;
import java.util.Optional;

final class ProdutoDaoImpl implements ProdutoDao {

    @Override
    public void insert(Connection connection, Produto produto) throws SQLException {
        final String sql = """
        BEGIN
        INSERT INTO T_PS_PRODUTO (cd_produto, vl_produto, ds_produto, nm_produto)
        VALUES (sq_t_ps_produto.NEXTVAL, ?, ?, ?)
        RETURNING cd_produto INTO ?;
        END;
        """;

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setDouble(1, produto.getPreco());
            cs.setString(2, produto.getDescricao());
            cs.setString(3, produto.getNome());
            cs.registerOutParameter(4, Types.NUMERIC);
            cs.execute();
            Long id = cs.getLong(4);
            produto.setId(id);
        }
    }

    @Override
    public Optional<Produto> selectByNome(Connection connection, String name) throws SQLException {
        String sql = """
        SELECT cd_produto, vl_produto, ds_produto, nm_produto 
        FROM T_PS_PRODUTO 
        WHERE nm_produto = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                            rs.getString("nm_produto"),
                            rs.getString("ds_produto"),
                            rs.getDouble("vl_produto")
                    );
                    produto.setId(rs.getLong("cd_produto"));
                    return Optional.of(produto);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Produto> selectById(Connection connection, Long id) throws SQLException {
        String sql = """
        SELECT cd_produto, vl_produto, ds_produto, nm_produto 
        FROM T_PS_PRODUTO 
        WHERE cd_produto = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(
                    1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                            rs.getString("nm_produto"),
                            rs.getString("ds_produto"),
                            rs.getDouble("vl_produto")
                    );
                    produto.setId(id);
                    return Optional.of(produto);
                }
            }
        }
        return Optional.empty();
    }
}
