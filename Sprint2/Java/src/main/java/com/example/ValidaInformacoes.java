package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidaInformacoes {

    public static boolean validaCPF(String cpf){
        if(cpf.contains(".")){
           cpf = cpf.replace(".", "");
        }
        if(cpf.contains("-")){
            cpf = cpf.replace("-", "");
        }
        if(cpf.length() != 11){
            return false;
        }
        for(int i = 0; i < 10; i++){
            if(cpf.equals(String.valueOf(i).repeat(11))){
                return false;
            }
        }
        List<Integer> digitos = new ArrayList<>(Arrays.stream(cpf.split("")).map(Integer::parseInt).toList());

        List<Integer> digitosVerificadores = new ArrayList<>(Arrays.asList(digitos.get(9), digitos.get(10)));
        digitos.remove(10   );
        digitos.remove(9);


       List<Integer> digitosAchado = new ArrayList<>();
       int soma = 0;
       for(int i = 1; i <= 9; i++){
           soma += digitos.get(i - 1) * i;
       }
       if(soma % 11 == 10){
           digitosAchado.add(0);
       } else {
           digitosAchado.add(soma % 11);
       }

       digitos.add(digitosAchado.getFirst());

       soma = 0;
       for(int i = 0; i <= 9; i++){
           soma += digitos.get(i) * i;
       }
       if(soma % 11 == 10){
           digitosAchado.add(0);
       } else {
           digitosAchado.add(soma % 11);
       }

       return digitosAchado.equals(digitosVerificadores);
    }

    public static boolean validaNome(String nome){
        if(nome.isBlank()){
            return false;
        }

        return (nome.matches("[A-Za-z].* [A-Za-z].*".trim()) || nome.matches("[a-zA-Z].*".trim()));
    }

    public static boolean validaEmail(String email){
        if(email.isBlank()){
            return false;
        }

        return (email.contains("@") && email.contains(".com"));
    }

    public static boolean validaSenha(String senha){
        if(senha.isBlank()){
            return false;
        }

        return senha.length() >= 8;
    }
}
