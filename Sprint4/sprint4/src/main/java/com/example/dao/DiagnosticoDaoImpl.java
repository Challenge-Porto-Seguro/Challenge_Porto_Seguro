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
                    Diagnostico diagnostico = instanciaDiagnostico(rs);
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
                    diagnosticos.add(instanciaDiagnostico(rs));

                }
            }
            return diagnosticos;
        }
    }

    private Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }

    private Automovel instanciaAutomovel(ResultSet rs) throws SQLException {
        Automovel automovel = new Automovel(rs.getString("nm_marca_veiculo"), rs.getString("nm_modelo_veiculo"), rs.getString("sq_placa"), rs.getDate("dt_veiculo").toLocalDate(), instaciaUsuario(rs));
        automovel.setId(rs.getLong("cd_automovel"));
        return automovel;
    }

    private Diagnostico instanciaDiagnostico(ResultSet rs) throws SQLException {
        Diagnostico diagnostico = new Diagnostico(rs.getString("nm_descricao_diagnostico"), rs.getDate("dt_inicio_diagnostico").toLocalDate(), VerificaDiagnostico.getDiagnostico(rs.getString("st_diagnostico")));
        diagnostico.setId(rs.getLong("cd_diagnostico"));
        diagnostico.setOficina(instaciaOficina(rs));
        diagnostico.setAutomovel(instanciaAutomovel(rs));
        diagnostico.setDataFinalizado(rs.getDate("dt_fim_diagnostico").toLocalDate());
        return diagnostico;
    }

    private Oficina instaciaOficina(ResultSet rs) throws SQLException {
        Oficina oficina = new Oficina(rs.getString("nm_nome"), rs.getString("sq_cnpj"), rs.getString("nm_email"), rs.getString("sq_senha"), rs.getString("sq_inscricao_estadual"));
        oficina.setId(rs.getLong("cd_oficina"));
        return oficina;
    }
}
