package com.example.service;

import com.example.exceptions.*;
import com.example.model.Automovel;

import java.sql.SQLException;
import java.util.List;

public interface AutomovelService {

    Automovel cadastraAutomovel(Automovel automovel) throws AutomovelNotCreate;

    Automovel alteraAutomovel(Automovel automovel) throws AutomovelNotFound, AutomovelNotUpdate;

    void excluiAutomovel(Long id) throws AutomovelNotFound, AutomovelNotDelete;

    Automovel buscaAutomovelPorId(Long id) throws AutomovelNotFound, SQLException;

    List<Automovel> listaAutomoveis(Long idUsuario) throws SQLException;
}
