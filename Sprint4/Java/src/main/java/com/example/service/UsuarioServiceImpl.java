package com.example.service;

import com.example.config.DatabaseConnectionFactory;
import com.example.dao.UsuarioDao;
import com.example.dao.UsuarioDaoFactory;
import com.example.exceptions.UsuarioNotCreate;
import com.example.exceptions.UsuarioNotDelete;
import com.example.exceptions.UsuarioNotFound;
import com.example.exceptions.UsuarioNotUpdate;
import com.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao repository = UsuarioDaoFactory.getUsuarioDao();

    @Override
    public Usuario cadastraUsuario(Usuario usuario) throws UsuarioNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            repository.insertUsuario(connection, usuario);
            return usuario;
        } catch (SQLException e) {
            throw new UsuarioNotCreate(e.getMessage());
        }
    }

    @Override
    public Usuario buscaUsuarioPorId(Long id) throws UsuarioNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findById(connection, id).orElseThrow(UsuarioNotFound::new);
    }

    @Override
    public Usuario alteraUsuario(Usuario usuario) throws UsuarioNotFound, UsuarioNotUpdate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            repository.updateUsuario(connection, usuario);
            return usuario;
        } catch (SQLException e){
            throw new UsuarioNotUpdate(e.getMessage());
        }
    }
    @Override
    public void excluiUsuario(Long id) throws UsuarioNotFound, UsuarioNotDelete {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            repository.deleteById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            throw new UsuarioNotDelete();
        }

    }

    @Override
    public List<Usuario> listaUsuarios() throws SQLException {
        Connection connection = DatabaseConnectionFactory.getConnection();
        return repository.findAll(connection);
    }
}
