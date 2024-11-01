package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.UsuarioDao;
import com.example.dao.UsuarioDaoFactory;
import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao repository = UsuarioDaoFactory.getUsuarioDao();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    private final EnderecoService enderecoService = EnderecoServiceFactory.getEnderecoService();

    @Override
    public Usuario cadastraUsuario(Usuario usuario, Endereco endereco) throws UsuarioNotCreate, SQLException, LoginNotCreate, CadastroInvalido, EnderecoNotCreate {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            loginService.cadastrar(connection, usuario);
            repository.insertUsuario(connection, usuario);
            endereco.setLogin(usuario.getId());
            enderecoService.cadastraEndereco(connection, endereco);
            connection.commit();
            return usuario;
        } catch (SQLException e) {
            connection.rollback();
            throw new UsuarioNotCreate(e.getMessage());
        } catch (LoginNotCreate | CadastroInvalido | EnderecoNotCreate e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Usuario buscaUsuarioPorId(Long id) throws UsuarioNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findById(connection, id).orElseThrow(UsuarioNotFound::new);
    }

    @Override
    public Usuario alteraUsuario(Usuario usuario, Endereco endereco) throws UsuarioNotFound, UsuarioNotUpdate, SQLException, LoginNotUpdade, LoginNotFound, EnderecoNotUpdate, EnderecoNotFound {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try {
            repository.updateUsuario(connection, usuario);
            loginService.update(connection, usuario);
            enderecoService.updateEndereco(connection, endereco);
            connection.commit();
            return usuario;
        } catch (SQLException e){
            connection.rollback();
            throw new UsuarioNotUpdate(e.getMessage());
        } catch (UsuarioNotFound | LoginNotUpdade | LoginNotFound | EnderecoNotUpdate | EnderecoNotFound e){
            connection.rollback();
            throw e;
        }
    }
    @Override
    public void excluiUsuario(Long id) throws UsuarioNotFound, UsuarioNotDelete, SQLException, EnderecoNotFound, EnderecoNotDelete {
        Connection connection = DatabaseConnectionFactory.getConnection();
        try{
            enderecoService.deleteByCdPessoa(connection, id);
            repository.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new UsuarioNotDelete();
        }

    }

    @Override
    public List<Usuario> listaUsuarios() throws SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findAll(connection);
    }
}
