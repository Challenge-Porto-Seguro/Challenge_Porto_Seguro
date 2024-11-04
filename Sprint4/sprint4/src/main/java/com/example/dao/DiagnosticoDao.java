package com.example.dao;

import com.example.exceptions.DiagnosticoNotFound;
import com.example.model.Diagnostico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DiagnosticoDao {

    void insertDiagnostico(Diagnostico diagnostico, Connection connection) throws SQLException;

    Optional<Diagnostico> getDiagnosticoById(Long id, Connection connection) throws SQLException;

    void updateDiagnostico(Diagnostico diagnostico, Connection connection) throws SQLException, DiagnosticoNotFound;

    List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa, Connection connection) throws SQLException;

    void deleteDiagnostico(Long id, Connection connection) throws SQLException, DiagnosticoNotFound;

    List<Diagnostico> getAllDiagnosticosByCdOficina(Long cdOficina, Connection connection) throws SQLException;

}
