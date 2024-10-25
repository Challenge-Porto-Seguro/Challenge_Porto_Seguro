package com.example.service;

import com.example.exceptions.*;
import com.example.model.Automovel;

import java.util.List;

public interface AutomovelService {

    Automovel cadastraAutomovel(Automovel automovel) throws AutomovelInvalido, AutomovelNotCreate;

    Automovel alteraAutomovel(Automovel automovel) throws AutomovelNotFound, AutomovelNotUpdate;

    void excluiAutomovel(Long id) throws AutomovelNotFound, AutomovelNotDelete;

    Automovel buscaAutomovelPorId(Long id) throws AutomovelNotFound;

    List<Automovel> listaAutomoveis(Long idUsuario) throws AutomovelInvalido;
}
