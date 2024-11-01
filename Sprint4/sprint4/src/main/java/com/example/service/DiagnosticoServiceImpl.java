package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.DiagnosticoDao;
import com.example.dao.DiagnosticoDaoFactory;
import com.example.exceptions.DiagnosticoNotFound;
import com.example.exceptions.DiagnoticoNotCreated;
import com.example.model.Diagnostico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class DiagnosticoServiceImpl implements DiagnosticoService {

    private DiagnosticoDao diagnosticoDao = DiagnosticoDaoFactory.createDiagnosticoDao();

    @Override
    public Diagnostico insertDiagnostico(Diagnostico diagnostico) throws DiagnoticoNotCreated {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            diagnosticoDao.insertDiagnostico(diagnostico, connection);
            connection.commit();
            return diagnostico;
        } catch (SQLException e) {
            throw new DiagnoticoNotCreated(e.getMessage());
        }
    }

    @Override
    public Diagnostico getDiagnosticoById(Long id) throws SQLException, DiagnosticoNotFound {
       try(Connection connection = DatabaseConnectionFactory.getConnection()) {
           Diagnostico diagnostico = diagnosticoDao.getDiagnosticoById(id, connection).orElseThrow(DiagnosticoNotFound::new);
           return diagnostico;
       }
    }

    @Override
    public Diagnostico updateDiagnostico(Diagnostico diagnostico) throws SQLException, DiagnosticoNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            diagnosticoDao.updateDiagnostico(diagnostico, connection);
            connection.commit();
            Diagnostico response = getDiagnosticoById(diagnostico.getId());
            return response;
        }
    }

    @Override
    public List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa) throws SQLException {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            List<Diagnostico> diagnosticos = diagnosticoDao.getAllDiagnosticosByCdPessoa(cdPessoa, connection);
            return diagnosticos;
        }
    }
}
