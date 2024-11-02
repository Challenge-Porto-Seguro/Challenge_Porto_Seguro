package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.ProdutoDao;
import com.example.dao.ProdutoDaoFactory;
import com.example.exceptions.ProdutoNotCreate;
import com.example.exceptions.ProdutoNotFound;
import com.example.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

final class ProdutoServiceImpl implements ProdutoService {

    private ProdutoDao produtoDao = ProdutoDaoFactory.getProdutoDao();

    @Override
    public void insert(Connection connection, Produto produto) throws ProdutoNotCreate, SQLException {
        try {
            Produto produtoExistente = selectByNome(produto.getNome());
            produto.setId(produtoExistente.getId());
            produto.setNome(produtoExistente.getNome());
            produto.setDescricao(produtoExistente.getDescricao());
            produto.setPreco(produtoExistente.getPreco());
        } catch (ProdutoNotFound  e) {
            try {
                produtoDao.insert(connection, produto);
            } catch (SQLException ex) {
                throw new ProdutoNotCreate(ex.getMessage() + Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    @Override
    public Produto selectByNome(String name) throws SQLException, ProdutoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return produtoDao.selectByNome(connection, name).orElseThrow(ProdutoNotFound::new);

    }

    @Override
    public Produto selectById(Long id) throws SQLException, ProdutoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return produtoDao.selectById(connection, id).orElseThrow(ProdutoNotFound::new);
    }
}
