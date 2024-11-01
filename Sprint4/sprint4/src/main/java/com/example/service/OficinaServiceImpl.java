package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.OficinaDao;
import com.example.dao.OficinaDaoFactory;
import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Oficina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class OficinaServiceImpl implements OficinaService {

    private OficinaDao oficinaDao = OficinaDaoFactory.getOficinaDao();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    private final EnderecoService enderecoService = EnderecoServiceFactory.getEnderecoService();

    @Override
    public Oficina cadastraOficina(Oficina oficina, Endereco endereco) throws OficinaNotCreate, SQLException, LoginNotCreate, CadastroInvalido, EnderecoNotCreate {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            loginService.cadastrar(connection, oficina);
            oficinaDao.insertOficina(connection, oficina);
            enderecoService.cadastraEndereco(connection, endereco);
            connection.commit();
            return oficina;
        } catch (SQLException e) {
            connection.rollback();
            throw new OficinaNotCreate(e.getMessage());
        } catch (LoginNotCreate | CadastroInvalido | EnderecoNotCreate e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Oficina buscaOficinaPorId(Long id) throws OficinaNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return oficinaDao.findById(connection, id).orElseThrow(OficinaNotFound::new);
    }

    @Override
    public Oficina alteraOficina(Oficina oficina, Endereco endereco) throws OficinaNotFound, OficinaNotUpdate, SQLException, LoginNotUpdade, LoginNotFound, EnderecoNotUpdate, EnderecoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            loginService.update(connection, oficina);
            oficinaDao.updateOficina(connection, oficina);
            endereco.setLogin(oficina.getId());
            enderecoService.updateEndereco(connection, endereco);
            connection.commit();
            return oficina;
        } catch (SQLException e){
            connection.rollback();
            throw new OficinaNotUpdate(e.getMessage());
        } catch (OficinaNotFound | LoginNotUpdade | LoginNotFound | EnderecoNotUpdate | EnderecoNotFound e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void excluiOficina(Long id) throws OficinaNotFound, OficinaNotDelete {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            oficinaDao.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            throw new OficinaNotDelete();
        }
    }

    @Override
    public List<Oficina> listaOficinas() throws SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return oficinaDao.findAll(connection);
    }
}
