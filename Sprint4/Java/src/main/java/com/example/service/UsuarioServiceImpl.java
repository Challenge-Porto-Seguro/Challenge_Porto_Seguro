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

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDao repository = UsuarioDaoFactory.getUsuarioDao();

    @Override
    public Usuario login(String email, String senha) {
        return null;
    }

    @Override
    public Usuario cadastraUsuario(Usuario usuario) throws UsuarioNotCreate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            repository.insertUsuario(connection, usuario);
            connection.commit();
            return usuario;
        } catch (SQLException e) {
            throw new UsuarioNotCreate();
        }
    }

    @Override
    public Usuario buscaUsuarioPorId(Long id) throws UsuarioNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return repository.findById(connection, id).orElseThrow(UsuarioNotFound::new);
        } catch (SQLException e){
            throw new UsuarioNotFound();
        }

    }

    @Override
    public Usuario alteraUsuario(Usuario usuario) throws UsuarioNotFound, UsuarioNotUpdate {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            repository.updateUsuario(connection, usuario);
            connection.commit();
            return usuario;
        } catch (SQLException e){
            throw new UsuarioNotUpdate();
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
    public List<Usuario> listaUsuarios() throws UsuarioNotFound {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            return repository.findAll(connection);
        } catch (SQLException e) {
            throw new UsuarioNotFound();
        }

    }
}
