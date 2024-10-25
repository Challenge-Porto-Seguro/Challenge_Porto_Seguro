package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.AutomovelDao;
import com.example.dao.AutomovelDaoFactory;
import com.example.exceptions.*;
import com.example.model.Automovel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class AutomovelServiceImpl implements AutomovelService {

    private final AutomovelDao repository = AutomovelDaoFactory.createAutomovelDao();

    @Override
    public Automovel cadastraAutomovel(Automovel automovel) throws AutomovelInvalido, AutomovelNotCreate {
        if (automovel.getId() != null) {
            throw new AutomovelInvalido();
        }
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.insert(connection, automovel);
            connection.commit();
            return automovel;
        } catch (SQLException e) {
            throw new AutomovelNotCreate();
        }
    }

    @Override
    public Automovel alteraAutomovel(Automovel automovel) throws AutomovelNotFound, AutomovelNotUpdate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.update(connection, automovel);
            connection.commit();
            return automovel;
        } catch (SQLException e) {
            throw new AutomovelNotUpdate();
        }
    }

    @Override
    public void excluiAutomovel(Long id) throws AutomovelNotFound, AutomovelNotDelete {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e){
            throw new AutomovelNotDelete();
        }

    }

    @Override
    public Automovel buscaAutomovelPorId(Long id) throws AutomovelNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return repository.findById(connection, id).orElseThrow(() -> new RuntimeException("Automovel não encontrado"));
        } catch (SQLException e) {
            throw new AutomovelNotFound();
        }

    }

    @Override
    public List<Automovel> listaAutomoveis(Long idUsuario) throws AutomovelInvalido {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return repository.findAll(connection, idUsuario);
        } catch (SQLException e) {
            throw new AutomovelInvalido();
        }

    }

}
