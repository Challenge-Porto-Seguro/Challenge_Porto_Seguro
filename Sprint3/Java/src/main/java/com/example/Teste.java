package com.example;

import com.example.model.Automovel;
import com.example.service.AutomovelService;
import com.example.service.UsuarioService;

import java.text.ParseException;
import java.util.Date;

public class Teste {
    public static void main(String[] args) throws ParseException {

        UsuarioService usuarioService = new UsuarioService();
//        Usuario usuario = new Usuario("Pedro", "12345678909", "ph6704938@gmail.com", "123456789");
//        usuarioService.cadastraUsuario(usuario);

        System.out.println(usuarioService.buscaUsuarioPorId(28L));

        AutomovelService automovelService = new AutomovelService();
        Automovel automovel = new Automovel("Tesla", "model x", "ABC1234", new Date(), usuarioService.buscaUsuarioPorId(28L));
        automovelService.cadastraAutomovel(automovel);

    }
}
