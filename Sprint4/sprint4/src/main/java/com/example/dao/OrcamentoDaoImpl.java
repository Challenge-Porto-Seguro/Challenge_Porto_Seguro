package com.example.dao;

import com.example.exceptions.OrcamentoNotFound;
import com.example.model.*;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Logger;

final class OrcamentoDaoImpl implements OrcamentoDao {

    private Logger logger = Logger.getLogger(OrcamentoDaoImpl.class.getName());

    @Override
    public void insertOrcamento(Connection connection, Orcamento orcamento) throws SQLException {
        final String sql = """
        BEGIN
            INSERT INTO T_PS_ORCAMENTO (cd_orcamento, st_orcamento, cd_diagnostico)
            VALUES (sq_t_ps_orcamento.NEXTVAL, ?, ?)
            RETURNING cd_orcamento INTO ?;
        END;
        """;

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, orcamento.getStatusOrcamento().getCodigo());
            cs.setLong(2, orcamento.getDiagnostico().getId());

            cs.registerOutParameter(3, Types.NUMERIC);

            cs.execute();
            Long id = cs.getLong(3);
            orcamento.setId(id);
        }
    }

    @Override
    public void updateOrcamento(Connection connection, Orcamento orcamento) throws SQLException, OrcamentoNotFound {
        String sql = """
            UPDATE T_PS_ORCAMENTO
            SET st_orcamento = ?
            WHERE cd_orcamento = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orcamento.getStatusOrcamento().getCodigo());
            ps.setLong(2, orcamento.getId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new OrcamentoNotFound();
            }
        }
    }

    @Override
    public void deleteOrcamento(Connection connection, Long id) throws SQLException, OrcamentoNotFound {
        String sql = """
        DELETE FROM T_PS_ORCAMENTO WHERE cd_orcamento = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new OrcamentoNotFound();
            }
        }
    }

    @Override
    public Optional<Orcamento> getOrcamentoByDiagnosticoId(Connection connection, Long diagnosticoId) throws SQLException {
        String sql = """
            SELECT o.*, d.*, a.*, u.*, p.*, f.*
            FROM T_PS_ORCAMENTO o
            JOIN T_PS_DIAGNOSTICO d ON o.cd_diagnostico = d.cd_diagnostico
            JOIN T_PS_AUTOMOVEL a ON d.cd_automovel = a.cd_automovel
            JOIN T_PS_USUARIO u ON a.cd_pessoa = u.cd_pessoa
            JOIN T_PS_PESSOA p ON u.cd_pessoa = p.cd_pessoa
            JOIN T_PS_OFICINA f ON f.cd_oficina = d.cd_oficina
            WHERE o.cd_diagnostico = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, diagnosticoId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(InstanciaObjetos.instanciaOrcamento(rs));
                }
            }
        }
        return Optional.empty();
    }

}
