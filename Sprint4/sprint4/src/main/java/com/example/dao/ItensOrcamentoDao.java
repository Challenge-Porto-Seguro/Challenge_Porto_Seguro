package com.example.dao;

import com.example.exceptions.ItensOrcamentoNotFound;
import com.example.model.ItensOrcamento;
import com.example.model.PKItensOrcamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ItensOrcamentoDao {

    void insertItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException;

    void updateItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException, ItensOrcamentoNotFound;

    void deleteItensOrcamento(Connection connection, Long idOrcamento) throws ItensOrcamentoNotFound, SQLException;

    List<ItensOrcamento> getItensOrcamento(Connection connection, Long orcamentoId) throws SQLException;
}
