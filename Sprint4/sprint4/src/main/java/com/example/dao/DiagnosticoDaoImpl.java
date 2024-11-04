package com.example.dao;

import com.example.exceptions.DiagnosticoNotFound;
import com.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class DiagnosticoDaoImpl implements DiagnosticoDao {

    @Override
    public void insertDiagnostico(Diagnostico diagnostico, Connection connection) throws SQLException {
        final String sql = """
                insert into T_PS_DIAGNOSTICO(cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, st_diagnostico, cd_oficina)
                values(?, ?, ?, ?, ?)
                """;
        try(PreparedStatement ps = connection.prepareStatement(sql, new String[] {"cd_diagnostico"})) {
            ps.setLong(1, diagnostico.getAutomovel().getId());
            ps.setString(2, diagnostico.getDescricao());
            ps.setDate(3, Date.valueOf(diagnostico.getData()));
            ps.setString(4, diagnostico.getStatus().getCodigo());
            ps.setLong(5, diagnostico.getOficina().getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        Long id = rs.getLong(1);
                        diagnostico.setId(id);
                    }
                }
            }
        }
    }

    @Override
    public Optional<Diagnostico> getDiagnosticoById(Long id, Connection connection) throws SQLException {
        final String sql = """
                select * from T_PS_DIAGNOSTICO d
                join t_ps_automovel a on(d.cd_automovel = a.cd_automovel)
                join t_ps_usuario u on(a.cd_pessoa = u.cd_pessoa)
                join t_ps_pessoa p on(u.cd_pessoa = p.cd_pessoa)
                join t_ps_oficina f on(f.cd_oficina = d.cd_oficina)
                where cd_diagnostico = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Diagnostico diagnostico = InstanciaObjetos.instanciaDiagnostico(rs);
                    return Optional.of(diagnostico);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateDiagnostico(Diagnostico diagnostico, Connection connection) throws SQLException, DiagnosticoNotFound {
        final String sql = """
                update T_PS_DIAGNOSTICO
                set dt_fim_diagnostico = ?, st_diagnostico = ?
                where cd_diagnostico = ?
                """;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setDate(1, Date.valueOf(diagnostico.getDataFinalizado()));
           ps.setString(2, diagnostico.getStatus().getCodigo());
           ps.setLong(3, diagnostico.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DiagnosticoNotFound();
            }
        }
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa, Connection connection) throws SQLException {
        final String sql = """
                select * from T_PS_DIAGNOSTICO d
                join t_ps_automovel a on(d.cd_automovel = a.cd_automovel)
                join t_ps_usuario u on(a.cd_pessoa = u.cd_pessoa)
                join t_ps_pessoa p on(u.cd_pessoa = p.cd_pessoa)
                join t_ps_oficina f on(f.cd_oficina = d.cd_oficina)
                where a.cd_pessoa = ?
                """;
        List<Diagnostico> diagnosticos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, cdPessoa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    diagnosticos.add(InstanciaObjetos.instanciaDiagnostico(rs));

                }
            }
            return diagnosticos;
        }
    }

    @Override
    public void deleteDiagnostico(Long id, Connection connection) throws SQLException, DiagnosticoNotFound {
        final String sql = """
        DELETE FROM T_PS_DIAGNOSTICO WHERE cd_diagnostico = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DiagnosticoNotFound();
            }
        }
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosByCdOficina(Long cdOficina, Connection connection) throws SQLException {
        final String sql = """
                select * from T_PS_DIAGNOSTICO d
                join t_ps_automovel a on(d.cd_automovel = a.cd_automovel)
                join t_ps_usuario u on(a.cd_pessoa = u.cd_pessoa)
                join t_ps_pessoa p on(u.cd_pessoa = p.cd_pessoa)
                join t_ps_oficina f on(f.cd_oficina = d.cd_oficina)
                where f.cd_oficina = ?
                """;
        List<Diagnostico> diagnosticos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, cdOficina);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    diagnosticos.add(InstanciaObjetos.instanciaDiagnostico(rs));

                }
            }
            return diagnosticos;
        }
    }
}
