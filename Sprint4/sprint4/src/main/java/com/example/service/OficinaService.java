package com.example.service;

import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Oficina;

import java.sql.SQLException;
import java.util.List;

public interface OficinaService {

    Oficina cadastraOficina(Oficina oficina, Endereco endereco) throws OficinaNotCreate, SQLException, LoginNotCreate, CadastroInvalido, EnderecoNotCreate;

    Oficina buscaOficinaPorId(Long id) throws OficinaNotFound, SQLException;

    Oficina alteraOficina(Oficina oficina, Endereco endereco) throws OficinaNotFound, OficinaNotUpdate, SQLException, LoginNotUpdade, LoginNotFound, EnderecoNotUpdate, EnderecoNotFound;

    void excluiOficina(Long id) throws OficinaNotFound, OficinaNotDelete;

    List<Oficina> listaOficinas() throws SQLException;
}
