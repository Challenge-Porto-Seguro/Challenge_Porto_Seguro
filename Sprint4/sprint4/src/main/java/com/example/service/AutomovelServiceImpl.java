package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.AutomovelDao;
import com.example.dao.AutomovelDaoFactory;
import com.example.exceptions.AutomovelNotCreate;
import com.example.exceptions.AutomovelNotDelete;
import com.example.exceptions.AutomovelNotFound;
import com.example.exceptions.AutomovelNotUpdate;
import com.example.model.Automovel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class AutomovelServiceImpl implements AutomovelService {

    private final AutomovelDao repository = AutomovelDaoFactory.createAutomovelDao();

    @Override
    public Automovel cadastraAutomovel(Automovel automovel) throws AutomovelNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.insert(connection, automovel);
            connection.commit();
            return automovel;
        } catch (SQLException e) {
            throw new AutomovelNotCreate(e.getMessage());
        }
    }

    @Override
    public Automovel alteraAutomovel(Automovel automovel) throws AutomovelNotFound, AutomovelNotUpdate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.update(connection, automovel);
            connection.commit();
            return automovel;
        } catch (SQLException e) {
            throw new AutomovelNotUpdate(e.getMessage());
        }
    }

    @Override
    public void excluiAutomovel(Long id) throws AutomovelNotFound, AutomovelNotDelete {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            this.repository.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e){
            throw new AutomovelNotDelete(e.getMessage());
        }

    }

    @Override
    public Automovel buscaAutomovelPorId(Long id) throws AutomovelNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findById(connection, id).orElseThrow(AutomovelNotFound::new);
    }

    @Override
    public List<Automovel> listaAutomoveis(Long idUsuario) throws SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findAll(connection, idUsuario);
    }

}
