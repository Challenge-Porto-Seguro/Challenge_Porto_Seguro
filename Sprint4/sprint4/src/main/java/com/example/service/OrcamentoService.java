package com.example.service;

import com.example.exceptions.OrcamentoNotFound;
import com.example.model.Orcamento;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrcamentoService {

    void insertOrcamento(Connection connection, Orcamento orcamento) throws SQLException;
    void updateOrcamento(Connection connection, Orcamento orcamento) throws SQLException, OrcamentoNotFound;
    void deleteOrcamento(Connection connection, Long id) throws SQLException, OrcamentoNotFound;
    Orcamento getOrcamentoByDiagnosticoId(Long diagnosticoId) throws SQLException, OrcamentoNotFound;
}
