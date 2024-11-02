package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.OrcamentoDao;
import com.example.dao.OrcamentoDaoFactory;
import com.example.exceptions.OrcamentoNotFound;
import com.example.model.Orcamento;

import java.sql.Connection;
import java.sql.SQLException;

final class OrcamentoServiceImpl implements OrcamentoService {

    private OrcamentoDao orcamentoDao = OrcamentoDaoFactory.getOrcamentoDao();

    @Override
    public void insertOrcamento(Connection connection, Orcamento orcamento) throws SQLException {
        orcamentoDao.insertOrcamento(connection, orcamento);
    }

    @Override
    public void updateOrcamento(Connection connection, Orcamento orcamento) throws SQLException, OrcamentoNotFound {
        orcamentoDao.updateOrcamento(connection, orcamento);
    }

    @Override
    public void deleteOrcamento(Connection connection, Long id) throws SQLException, OrcamentoNotFound {
        orcamentoDao.deleteOrcamento(connection, id);
    }

    @Override
    public Orcamento getOrcamentoByDiagnosticoId(Long diagnosticoId) throws SQLException, OrcamentoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return orcamentoDao.getOrcamentoByDiagnosticoId(connection, diagnosticoId).orElseThrow(OrcamentoNotFound::new);
    }
}
