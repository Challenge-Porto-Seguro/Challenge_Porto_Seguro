package com.example;

import com.example.db.dao.AutomovelDao;
import com.example.db.dao.DaoFactory;
import com.example.model.Automovel;
import com.example.model.usuarios.Usuario;
import com.example.service.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Teste {
    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        AutomovelDao automovelDao = DaoFactory.createAutomovelDao();
//
//        System.out.println("Teste 2");
//        System.out.println(automovelDao.findById(1));
//
//        System.out.println("Teste 3");
//        Automovel automovel1 = automovelDao.findById(1).get();
//        System.out.println(automovel1);
//        automovel1.setMarca("Ola mundo");
//        automovelDao.update(automovel1);
//        System.out.println(automovelDao.findById(1).get());

        UsuarioService usuarioService = new UsuarioService();
//        Usuario usuario = new Usuario("Pedro", "12345678909", "ph6704938@gmail.com", "123456789");
//        usuarioService.cadastraUsuario(usuario);

        System.out.println(usuarioService.buscaUsuarioPorId(28L));
    }
}
