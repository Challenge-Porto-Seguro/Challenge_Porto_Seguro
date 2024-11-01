package com.example.service;

import com.example.exceptions.DiagnosticoNotFound;
import com.example.exceptions.DiagnoticoNotCreated;
import com.example.model.Diagnostico;

import java.sql.SQLException;
import java.util.List;

public interface DiagnosticoService {

    Diagnostico insertDiagnostico(Diagnostico diagnostico) throws DiagnoticoNotCreated;

    Diagnostico getDiagnosticoById(Long id) throws SQLException, DiagnosticoNotFound;

    Diagnostico updateDiagnostico(Diagnostico diagnostico) throws SQLException, DiagnosticoNotFound;

    List<Diagnostico> getAllDiagnosticosByCdPessoa(Long cdPessoa) throws SQLException;
}
