package com.example.dao;

import com.example.exceptions.ItensOrcamentoNotFound;
import com.example.model.ItensOrcamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class ItensOrcamentoDaoImpl implements ItensOrcamentoDao {

    @Override
    public void insertItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException {
        String sql = """
        INSERT INTO T_PS_ITENS_ORCAMENTO (cd_orcamento, cd_produto, qt_pedido, vl_pedido) 
        VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, itensOrcamento.getId().getIdOrcamento());
            ps.setLong(2, itensOrcamento.getId().getIdProduto());
            ps.setInt(3, itensOrcamento.getQuantidade());
            ps.setDouble(4, itensOrcamento.getValorTotal());
            ps.executeUpdate();
        }
    }


    @Override
    public void updateItensOrcamento(Connection connection, ItensOrcamento itensOrcamento) throws SQLException, ItensOrcamentoNotFound {
        String sql = """
        UPDATE T_PS_ITENS_ORCAMENTO 
        SET qt_pedido = ?, vl_pedido = ? 
        WHERE cd_orcamento = ? AND cd_produto = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, itensOrcamento.getQuantidade());
            ps.setDouble(2, itensOrcamento.getValorTotal());
            ps.setLong(3, itensOrcamento.getId().getIdOrcamento());
            ps.setLong(4, itensOrcamento.getId().getIdProduto());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                throw new ItensOrcamentoNotFound();
            }
        }
    }

    @Override
    public void deleteItensOrcamento(Connection connection, Long idOrcamento) throws ItensOrcamentoNotFound, SQLException {
        String sql = """
        DELETE FROM T_PS_ITENS_ORCAMENTO 
        WHERE cd_orcamento = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idOrcamento);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                throw new ItensOrcamentoNotFound();
            }
        }
    }

    @Override
    public List<ItensOrcamento> getItensOrcamento(Connection connection, Long orcamentoId) throws SQLException {
        String sql = """
        SELECT o.*, d.*, a.*, u.*, p.*, f.*, i.*, pessoa.*
        FROM T_PS_ITENS_ORCAMENTO i
        JOIN T_PS_ORCAMENTO o ON i.cd_orcamento = o.cd_orcamento
        JOIN T_PS_DIAGNOSTICO d ON o.cd_diagnostico = d.cd_diagnostico
        JOIN T_PS_AUTOMOVEL a ON d.cd_automovel = a.cd_automovel
        JOIN T_PS_USUARIO u ON a.cd_pessoa = u.cd_pessoa
        JOIN T_PS_PESSOA pessoa ON u.cd_pessoa = pessoa.cd_pessoa
        JOIN T_PS_OFICINA f ON f.cd_oficina = d.cd_oficina
        JOIN T_PS_PRODUTO p ON i.cd_produto = p.cd_produto
        WHERE i.cd_orcamento = ? 
        """;
        List<ItensOrcamento> itensOrcamentos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, orcamentoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    itensOrcamentos.add(InstanciaObjetos.instanciaItensOrcamento(rs));
                }
            }
        }
        return itensOrcamentos;
    }
}
