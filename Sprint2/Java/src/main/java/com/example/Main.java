package com.example;

import com.example.domain.*;
import com.example.domain.usuarios.Mecanico;
import com.example.domain.usuarios.UsuarioComum;
import com.example.domain.usuarios.UsuarioPorto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Endereco e = new Endereco("Elis Regina", 104, "05273-010", "Morro Doce", "São Paulo", "São Paulo");
            UsuarioComum uc = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", e);
            for(int i = 0; i < 3; i++){
                Orcamento o = new Orcamento();
                o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));
                uc.addOrcamento(o);
            }

            System.out.println("Quantidade de orçamentos do Usuario Comum: " + uc.getQuantidadeOrcamento());

            System.out.println("Alterar senha");
            System.out.print("Digite seu cpf para confirmar que é você: ");
            String cpf = sc.nextLine();

            System.out.print("Digite a nova senha: ");
            String novaSenha = sc.nextLine();
            uc.alterarSenha(cpf, novaSenha);


        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

        try {
            Endereco e1 = new Endereco("Elis Regina", 104, "05273-010", "Morro Doce", "São Paulo", "São Paulo");
            UsuarioPorto up = new UsuarioPorto("Pedro", "12345678909", "ph@gmail.com", "12345678", e1, "1");
            for(int i = 0; i < 7; i++){
                Orcamento o = new Orcamento();
                o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));
                up.addOrcamento(o);
            }

            System.out.println("\nQuantidade de orçamentos do Usuarios Porto: " + up.getQuantidadeOrcamento());

        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

        try {
            Endereco e1 = new Endereco("Elis Regina", 104, "05273-010", "Morro Doce", "São Paulo", "São Paulo");
            Mecanico mc = new Mecanico("Pedro", "12345678909", "ph@gmail.com", "12345678", e1, "Mecanica Ferraz");
            for(int i = 0; i < 10; i++){
                Orcamento o = new Orcamento();
                o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));
                mc.addOrcamento(o);
            }


        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

        Diagnostico diagnostico = new Diagnostico("oi");
        System.out.println(diagnostico);

    }
}