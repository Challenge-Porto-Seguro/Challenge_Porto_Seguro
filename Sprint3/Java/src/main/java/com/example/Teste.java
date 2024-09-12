package com.example;

import com.example.model.Automovel;
import com.example.model.usuarios.Usuario;
import com.example.service.AutomovelService;
import com.example.service.UsuarioService;

import java.text.ParseException;
import java.util.Date;

public class Teste {
    public static void main(String[] args) throws ParseException {

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Pedro", "12345678909", "ph6704938@gmail.com", "123456789");
//        usuarioService.cadastraUsuario(usuario);

        System.out.println(usuarioService.buscaUsuarioPorId(1L));
        usuario = usuarioService.buscaUsuarioPorId(1L);
        usuario.setEmail("pedro@gmail.com");
        usuarioService.alteraUsuario(usuario);

        System.out.println(usuarioService.buscaUsuarioPorId(1L));

        usuario.setNome("Joao");
        usuarioService.alteraUsuario(usuario);
        System.out.println(usuarioService.buscaUsuarioPorId(1L));

        AutomovelService automovelService = new AutomovelService();
        Automovel automovel = new Automovel("Tesla", "model x", "ABC1234", new Date(), usuarioService.buscaUsuarioPorId(1L));
//        automovelService.cadastraAutomovel(automovel);

        System.out.println(automovelService.buscaAutomovelPorId(1L));

    }
}
