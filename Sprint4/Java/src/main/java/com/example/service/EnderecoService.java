package com.example.service;

import com.example.exceptions.EnderecoNotCreate;
import com.example.exceptions.EnderecoNotDelete;
import com.example.exceptions.EnderecoNotFound;
import com.example.exceptions.EnderecoNotUpdate;
import com.example.model.Endereco;

import java.sql.SQLException;

public interface EnderecoService {

    Endereco cadastraEndereco(Endereco endereco) throws EnderecoNotCreate;
    Endereco updateEndereco(Endereco endereco) throws EnderecoNotFound, EnderecoNotUpdate;
    void deleteById(Long id) throws EnderecoNotFound, SQLException, EnderecoNotDelete;
    Endereco buscaEnderecoPorId(Long id) throws SQLException, EnderecoNotFound;

}
