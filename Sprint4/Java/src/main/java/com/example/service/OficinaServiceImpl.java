package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.OficinaDao;
import com.example.dao.OficinaDaoFactory;
import com.example.exceptions.*;
import com.example.model.Oficina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class OficinaServiceImpl implements OficinaService {

    private OficinaDao oficinaDao = OficinaDaoFactory.getOficinaDao();

    @Override
    public Oficina cadastraOficina(Oficina oficina) throws OficinaNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            oficinaDao.insertOficina(connection, oficina);
            return oficina;
        } catch (SQLException e) {
            throw new OficinaNotCreate(e.getMessage());
        }
    }

    @Override
    public Oficina buscaOficinaPorId(Long id) throws OficinaNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return oficinaDao.findById(connection, id).orElseThrow(OficinaNotFound::new);
    }

    @Override
    public Oficina alteraOficina(Oficina oficina) throws OficinaNotFound, OficinaNotUpdate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            oficinaDao.updateOficina(connection, oficina);
            return oficina;
        } catch (SQLException e){
            throw new OficinaNotUpdate(e.getMessage());
        }
    }

    @Override
    public void excluiOficina(Long id) throws OficinaNotFound, OficinaNotDelete {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            oficinaDao.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            throw new OficinaNotDelete();
        }
    }

    @Override
    public List<Oficina> listaOficinas() throws SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return oficinaDao.findAll(connection);
    }
}
