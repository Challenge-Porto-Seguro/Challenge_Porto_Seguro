package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.*;
import com.example.dto.DiagnosticoByIdResponse;
import com.example.dto.ItensProdutoDto;
import com.example.exceptions.*;
import com.example.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

final class DiagnosticoServiceImpl implements DiagnosticoService {

    private DiagnosticoDao diagnosticoDao = DiagnosticoDaoFactory.createDiagnosticoDao();
    private OrcamentoService orcamentoService = OrcamentoServiceFactory.getOrcamentoService();
    private ItensOrcamentoService itensOrcamentoService = ItensOrcamentoServiceFactory.getItensOrcamentoService();
    private ProdutoService produtoService = ProdutoServiceFactory.getProdutoService();

    @Override
    public Diagnostico insertDiagnostico(Diagnostico diagnostico, Orcamento orcamento, Produto produto, ItensOrcamento itensOrcamento) throws DiagnoticoNotCreated, SQLException, ProdutoNotCreate, ProdutoNotFound, MaximoDiagnosticoException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            List<Diagnostico> allDiagnosticosByCdPessoa = diagnosticoDao.getAllDiagnosticosByCdPessoa(diagnostico.getAutomovel().getUsuario().getId(), connection);
            if (allDiagnosticosByCdPessoa.size() >= 3) {
                long diagnosticoMes = 0;
                int mesAtual = LocalDate.now().getMonthValue();
                int anoAtual = LocalDate.now().getYear();
                for (Diagnostico d : allDiagnosticosByCdPessoa) {
                    if (d.getData() != null && d.getData().getMonthValue() == mesAtual && d.getData().getYear() == anoAtual) {
                        diagnosticoMes++;
                    }
                }
                if (diagnosticoMes >= 3){
                    throw new MaximoDiagnosticoException("Máximo de três orçamentos já atingido para este mês.");
                }
            }
            diagnosticoDao.insertDiagnostico(diagnostico, connection);
            orcamentoService.insertOrcamento(connection, orcamento);
            produtoService.insert(connection, produto);
            itensOrcamento.getId().setOrcamento(orcamento);
            itensOrcamento.getId().setProduto(produto);
            itensOrcamentoService.insertItensOrcamento(connection, itensOrcamento);
            connection.commit();
            return diagnostico;
        } catch (SQLException e) {
            connection.rollback();
            throw new DiagnoticoNotCreated(e.getMessage());
        } catch (ProdutoNotCreate e) {
            connection.rollback();
            throw e;
        } catch (MaximoDiagnosticoException e) {
            connection.rollback();
            throw new MaximoDiagnosticoException(e.getMessage());
        }
    }

    @Override
    public DiagnosticoByIdResponse getDiagnosticoById(Long id) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound, ProdutoNotFound {
       try(Connection connection = DatabaseConnectionFactory.getConnection()) {
           Diagnostico diagnostico = diagnosticoDao.getDiagnosticoById(id, connection).orElseThrow(DiagnosticoNotFound::new);
           Orcamento orcamento = orcamentoService.getOrcamentoByDiagnosticoId(id);
           List<ItensOrcamento> itensOrcamento = itensOrcamentoService.getItensOrcamento(orcamento.getId());
           List<ItensProdutoDto> produtos = new ArrayList<>();
           for (ItensOrcamento itens : itensOrcamento) {
               Produto produto = produtoService.selectById(itens.getId().getIdProduto());
               produtos.add(new ItensProdutoDto(produto.getNome(), produto.getPreco(), itens.getQuantidade()));
           }
           orcamento.setPedidos(itensOrcamento);
           String date;
           try {
               date = diagnostico.getDataFinalizado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           } catch (RuntimeException e) {
               date = null;
           }
           DiagnosticoByIdResponse diagnosticoByIdResponse = new DiagnosticoByIdResponse(diagnostico.getId(),
                   diagnostico.getAutomovel().getId(), diagnostico.getDescricao(), diagnostico.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                   diagnostico.getOficina().getId(), date,
                   diagnostico.getStatus().toString(), produtos, orcamento.getValorTotal());
           return diagnosticoByIdResponse;
       }
    }

    @Override
    public Diagnostico updateDiagnostico(Diagnostico diagnostico) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            diagnosticoDao.updateDiagnostico(diagnostico, connection);
            Orcamento orcamento = orcamentoService.getOrcamentoByDiagnosticoId(diagnostico.getId());
            orcamento.setStatus(StatusOrcamento.INATIVO);
            orcamentoService.updateOrcamento(connection, orcamento);
            connection.commit();
            return diagnosticoDao.getDiagnosticoById(diagnostico.getId(), connection).orElseThrow(DiagnosticoNotFound::new);
        } catch (SQLException | DiagnosticoNotFound | OrcamentoNotFound e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa) throws SQLException {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            List<Diagnostico> diagnosticos = diagnosticoDao.getAllDiagnosticosByCdPessoa(cdPessoa, connection);
            return diagnosticos;
        }
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosByCdOficina(Long cdOficina) throws SQLException {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            List<Diagnostico> diagnosticos = diagnosticoDao.getAllDiagnosticosByCdOficina(cdOficina, connection);
            return diagnosticos;
        }
    }

    @Override
    public void deleteDiagnostico(Long id) throws SQLException, DiagnosticoNotFound, OrcamentoNotFound, ItensOrcamentoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            Orcamento orcamento = orcamentoService.getOrcamentoByDiagnosticoId(id);
            itensOrcamentoService.deleteItensOrcamento(connection, orcamento.getId());
            orcamentoService.deleteOrcamento(connection, orcamento.getId());
            diagnosticoDao.deleteDiagnostico(id, connection);
            connection.commit();
        } catch (SQLException | DiagnosticoNotFound | OrcamentoNotFound | ItensOrcamentoNotFound e){
            connection.rollback();
            throw e;
        }

    }
}
