package com.example;

import com.example.domain.Endereco;
import com.example.domain.Orcamento;
import com.example.domain.Pedido;
import com.example.domain.Produto;
import com.example.domain.usuarios.UsuarioComum;
import com.example.domain.usuarios.UsuarioPorto;

public class Main {
    public static void main(String[] args) {

        try {
            Endereco e = new Endereco("Elis Regina", 104, "05273-010", "Morro Doce", "São Paulo", "São Paulo");
            UsuarioComum uc = new UsuarioComum("Pedro", "12345678909", "ph@gmail.com", "12345678", e);
            for(int i = 0; i < 4; i++){
                Orcamento o = new Orcamento();
                o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));
                uc.addOrcamento(o);
                System.out.println(uc.getQuantidadeOrcamento());
            }

        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

        try {
            Endereco e1 = new Endereco("Elis Regina", 104, "05273-010", "Morro Doce", "São Paulo", "São Paulo");
            UsuarioPorto up = new UsuarioPorto("Pedro", "12345678909", "ph@gmail.com", "12345678", e1, "1");
            for(int i = 0; i < 8; i++){
                Orcamento o = new Orcamento();
                o.addPedido(new Pedido(3, new Produto("Pedro", "de", 2)));
                up.addOrcamento(o);
                System.out.println(up.getQuantidadeOrcamento());
            }

        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }



    }
}