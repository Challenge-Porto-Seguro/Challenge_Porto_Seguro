package com.example.service;

import com.example.exceptions.*;
import com.example.model.Oficina;

import java.sql.SQLException;
import java.util.List;

public interface OficinaService {

    Oficina cadastraOficina(Oficina oficina) throws OficinaNotCreate;

    Oficina buscaOficinaPorId(Long id) throws OficinaNotFound, SQLException;

    Oficina alteraOficina(Oficina oficina) throws OficinaNotFound, OficinaNotUpdate;

    void excluiOficina(Long id) throws OficinaNotFound, OficinaNotDelete;

    List<Oficina> listaOficinas() throws SQLException;
}
