package com.example.service;

import com.example.dto.DiagnosticoByIdResponse;
import com.example.exceptions.*;
import com.example.model.Diagnostico;
import com.example.model.ItensOrcamento;
import com.example.model.Orcamento;
import com.example.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DiagnosticoService {

    Diagnostico insertDiagnostico(Diagnostico diagnostico, Orcamento orcamento, Produto produto, ItensOrcamento itensOrcamento) throws DiagnoticoNotCreated, SQLException, ProdutoNotCreate, ProdutoNotFound, MaximoDiagnosticoException;

    DiagnosticoByIdResponse getDiagnosticoById(Long id) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound, ProdutoNotFound;

    Diagnostico updateDiagnostico(Diagnostico diagnostico) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound;

    List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa) throws SQLException;

    List<Diagnostico> getAllDiagnosticosByCdOficina(Long cdOficina) throws SQLException;

    void deleteDiagnostico(Long id) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound, ItensOrcamentoNotFound;
}
