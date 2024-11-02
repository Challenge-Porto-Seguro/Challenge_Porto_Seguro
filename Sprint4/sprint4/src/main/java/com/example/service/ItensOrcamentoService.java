package com.example.service;

import com.example.exceptions.ItensOrcamentoNotFound;
import com.example.model.ItensOrcamento;
import com.example.model.PKItensOrcamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItensOrcamentoService {

    void insertItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException;

    void updateItensOrcamento(ItensOrcamento itensOrcamento) throws SQLException, ItensOrcamentoNotFound;

    void deleteItensOrcamento(Connection connection, Long idOrcamento) throws ItensOrcamentoNotFound, SQLException;

    List<ItensOrcamento> getItensOrcamento(Long orcamentoId) throws SQLException;
}
