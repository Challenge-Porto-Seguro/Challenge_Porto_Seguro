package com.example.dao;

import com.example.exceptions.OrcamentoNotFound;
import com.example.model.Orcamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface OrcamentoDao {

    void insertOrcamento(Connection connection, Orcamento orcamento) throws SQLException;
    void updateOrcamento(Connection connection, Orcamento orcamento) throws SQLException, OrcamentoNotFound;
    void deleteOrcamento(Connection connection, Long id) throws SQLException, OrcamentoNotFound;
    Optional<Orcamento> getOrcamentoByDiagnosticoId(Connection connection, Long diagnosticoId) throws SQLException;
}
