package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.EnderecoDao;
import com.example.dao.EnderecoDaoFactory;
import com.example.exceptions.EnderecoNotCreate;
import com.example.exceptions.EnderecoNotDelete;
import com.example.exceptions.EnderecoNotFound;
import com.example.exceptions.EnderecoNotUpdate;
import com.example.model.Endereco;

import java.sql.Connection;
import java.sql.SQLException;

final class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoDao enderecoDao = EnderecoDaoFactory.getEnderecoDao();

    @Override
    public Endereco cadastraEndereco(Connection connection, Endereco endereco) throws EnderecoNotCreate {
        try {
            enderecoDao.insertEndereco(connection, endereco);
            return endereco;
        } catch (SQLException e) {
            throw new EnderecoNotCreate(e.getMessage());
        }
    }

    @Override
    public Endereco updateEndereco(Connection connection, Endereco endereco) throws EnderecoNotFound, EnderecoNotUpdate {
        try {
            enderecoDao.updateEndereco(connection, endereco);
            return endereco;
        } catch (SQLException e) {
            throw new EnderecoNotUpdate(e.getMessage());
        }
    }

    @Override
    public void deleteByCdPessoa(Connection connection, Long id) throws EnderecoNotFound, EnderecoNotDelete {
        try {
            enderecoDao.deleteByCdPessoa(connection, id);
        } catch (SQLException e) {
            throw new EnderecoNotDelete();
        }
    }

    @Override
    public Endereco buscaEnderecoPorId(Long id) throws EnderecoNotFound, SQLException {
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
            return enderecoDao.findById(connection, id)
                    .orElseThrow(EnderecoNotFound::new);
        }
    }
}
