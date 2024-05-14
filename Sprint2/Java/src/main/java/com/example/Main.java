package com.example;

import com.example.domain.Endereco;
import com.example.domain.Orcamento;
import com.example.domain.Pedido;
import com.example.domain.Produto;
import com.example.domain.usuarios.UsuarioComum;
import com.example.domain.usuarios.UsuarioPorto;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println(ValidaInformacoes.validaCPF("12345678912"));
        System.out.println(ValidaInformacoes.validaEmail("ph@gmail.com"));
        System.out.println(ValidaInformacoes.validaNome("Pedro"));
        System.out.println(ValidaInformacoes.validaSenha("1234567"));

        Endereco e = new Endereco("Elis Regina", 104, "05273010", "Morro Doce", "São Paulo", "São Paulo");


        try {

            UsuarioComum up = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", e);
            Orcamento o = new Orcamento();
            o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));

            Orcamento o2 = new Orcamento();
            o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));

            Orcamento o3 = new Orcamento();
            o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));

            Orcamento o4 = new Orcamento();
            o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));

            up.addOrcamento(o);
            up.addOrcamento(o2);
            System.out.println(up.getQuantidadeOrcamento());
            up.addOrcamento(o3);
            System.out.println(up.getQuantidadeOrcamento());
            up.addOrcamento(o4);
            System.out.println(up.getOrcamentos());
            System.out.println(up.getQuantidadeOrcamento());


        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

    }
}