package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.ItensOrcamentoDao;
import com.example.dao.ItensOrcamentoDaoFactory;
import com.example.exceptions.ItensOrcamentoNotFound;
import com.example.model.ItensOrcamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class ItensOrcamentoServiceImpl implements ItensOrcamentoService {

    private ItensOrcamentoDao itensOrcamentoDao = ItensOrcamentoDaoFactory.getItensOrcamentoDao();

    @Override
    public void insertItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException {
        itensOrcamentoDao.insertItensOrcamento(connection, itensOrcamento);
    }

    @Override
    public void updateItensOrcamento(ItensOrcamento itensOrcamento) throws SQLException, ItensOrcamentoNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            itensOrcamentoDao.updateItensOrcamento(connection, itensOrcamento);
            connection.commit();
        }
    }

    @Override
    public void deleteItensOrcamento(Connection connection, Long idOrcamento) throws ItensOrcamentoNotFound, SQLException {
        itensOrcamentoDao.deleteItensOrcamento(connection, idOrcamento);
    }

    @Override
    public List<ItensOrcamento> getItensOrcamento(Long orcamentoId) throws SQLException {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return itensOrcamentoDao.getItensOrcamento(connection, orcamentoId);
        }

    }
}
