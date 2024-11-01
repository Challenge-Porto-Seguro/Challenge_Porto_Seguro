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
    public Endereco cadastraEndereco(Endereco endereco) throws EnderecoNotCreate {
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
            enderecoDao.insertEndereco(connection, endereco);
            connection.commit();
            return endereco;
        } catch (SQLException e) {
            throw new EnderecoNotCreate(e.getMessage());
        }
    }

    @Override
    public Endereco updateEndereco(Endereco endereco) throws EnderecoNotFound, EnderecoNotUpdate {
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
            enderecoDao.updateEndereco(connection, endereco);
            connection.commit();
            return endereco;
        } catch (SQLException e) {
            throw new EnderecoNotUpdate(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) throws EnderecoNotFound, EnderecoNotDelete {
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
            enderecoDao.deleteById(connection, id);
            connection.commit();
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
