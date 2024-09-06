package com.example.validacoes;

public class ValidaInformacoesLogin {


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
