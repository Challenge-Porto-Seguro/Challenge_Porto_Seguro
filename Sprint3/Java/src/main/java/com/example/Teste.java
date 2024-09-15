package com.example;

import com.example.model.*;
import com.example.model.usuarios.Usuario;
import com.example.service.AutomovelService;
import com.example.service.UsuarioService;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) throws ParseException {

        UsuarioService usuarioService = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        boolean informacoesValida = false;
        Usuario usuario = null;
        while (!informacoesValida) {
            System.out.println("Cadastro");

            System.out.println("Digite o nome do usuario que deseja cadastrar: ");
            String nome = scanner.nextLine();

            System.out.println("Digite o cpf do usuario que deseja cadastrar: ");
            String cpf = scanner.nextLine();

            System.out.println("Digite o email do usuario que deseja cadastrar: ");
            String email = scanner.nextLine();

            System.out.println("Digite a senha do usuario que deseja cadastrar: ");
            String senha = scanner.nextLine();

            try {
                usuario = new Usuario(nome, cpf, email, senha);
                informacoesValida = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        usuarioService.cadastraUsuario(usuario);

        usuarioService.listaUsuarios().forEach(System.out::println);

        System.out.println("Escolha o id do usuario que deseja buscar");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(usuarioService.buscaUsuarioPorId(id));

        usuario = usuarioService.buscaUsuarioPorId(id);
        System.out.println("Digite para qual email deseja alterar: ");
        String email = scanner.nextLine();
        try {
            usuario.setEmail(email);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        usuarioService.alteraUsuario(usuario);

        System.out.println(usuarioService.buscaUsuarioPorId(id));
        System.out.println();

//        usuario.setNome("Joao");
//        usuarioService.alteraUsuario(usuario);
//        System.out.println(usuarioService.buscaUsuarioPorId(1L));

        System.out.println("Digite o id do usuario que deseja deletar");
        Long newId = scanner.nextLong();
        scanner.nextLine();
        usuarioService.excluiUsuario(newId);


        AutomovelService automovelService = new AutomovelService();
        Automovel automovel = null;
        informacoesValida = false;
        while (!informacoesValida) {
            System.out.println("Cadastro de automovel");
            System.out.println("Digite a marca do automovel que deseja cadastrar: ");
            String marca = scanner.nextLine();

            System.out.println("Digite o modelo do automovel que deseja cadastrar: ");
            String modelo = scanner.nextLine();
            System.out.println("Digite a placa do automovel que deseja cadastrar: ");
            String placa = scanner.nextLine();
            try {
                automovel = new Automovel(marca, modelo, placa, new Date(), usuarioService.buscaUsuarioPorId(id));
                informacoesValida = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }


        automovelService.cadastraAutomovel(automovel);

        System.out.println(automovelService.buscaAutomovelPorId(1L));
        System.out.println();

        System.out.println("Testando email invalido");
        try {
            Usuario mockUsuario = new Usuario("Pedro", "123456", "ph@gmail", "12345678");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("Testando cpf invalido");
        try {
            Usuario mockUsuario = new Usuario("Pedro", "123456", "ph@gmail.com", "12345678");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("Testando senha invalida");
        try {
            Usuario mockUsuario = new Usuario("Pedro", "12345678909", "ph@gmail.com", "123456");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println();

        Orcamento orcamento = new Orcamento();
        Produto produto = new Produto("teste", "teste", 12.5);
        Pedido pedido = new Pedido(2, produto);
        orcamento.addPedido(pedido);

        Orcamento orcamento2 = new Orcamento();
        Produto produto2 = new Produto("teste", "teste", 12.5);
        Pedido pedido2 = new Pedido(2, produto2);
        orcamento.addPedido(pedido2);

        Orcamento orcamento3 = new Orcamento();
        Produto produto3 = new Produto("teste", "teste", 12.5);
        Pedido pedido3 = new Pedido(2, produto3);
        orcamento.addPedido(pedido3);

        Orcamento orcamento4 = new Orcamento();
        Produto produto4 = new Produto("teste", "teste", 12.5);
        Pedido pedido4 = new Pedido(2, produto4);
        orcamento.addPedido(pedido4);

        Usuario mockUsuario = usuarioService.buscaUsuarioPorId(1L);
        Automovel mockAutomovel = automovelService.buscaAutomovelPorId(1L);
        Diagnostico diagnostico = new Diagnostico("teste");
        mockAutomovel.setDiagnostico(diagnostico);
        mockUsuario.addAutomovel(mockAutomovel);

        mockUsuario.addOrcamento(1L, orcamento);
        mockUsuario.addOrcamento(1L, orcamento2);
        mockUsuario.addOrcamento(1L, orcamento3);

        System.out.println("testando limite de orçamento atingido");
        try {
            mockUsuario.addOrcamento(1L, orcamento4);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println();

        automovelService.listaAutomoveis().forEach(System.out::println);

    }
}
