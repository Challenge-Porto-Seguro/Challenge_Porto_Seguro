package com.example.domain;

import com.example.ValidaInformacoesUsuario;

import java.util.ArrayList;
import java.util.List;

public class Cpf {

    private String cpf;

    public Cpf(String cpf) {
        verificaSeCpfEValido(cpf);
        this.cpf = desformatarCpf(cpf);
    }

    private String desformatarCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public void verificaSeCpfEValido(String cpf){
        if(!ValidaInformacoesUsuario.validaCPF(cpf)){
            throw new RuntimeException("CPF invalido");
        }
    }

    public String getCpf() {
        return cpf;
    }

    private String cpfFormatado() {
        List<String> list = new ArrayList<>();
        int num = 1;
        for(int i = 0; i < cpf.length(); i++){
            list.add(String.valueOf(cpf.charAt(i)));
            if(num % 3 == 0 && num != 9){
                list.add(".");
            } else if((num == 9)) {
                list.add("-");
            }
            num++;
        }

        StringBuilder cpfFormatado = new StringBuilder();
        for(String s : list){
            cpfFormatado.append(s);
        }

        return cpfFormatado.toString();
    }

    @Override
    public String toString() {
        return "CPF: " + cpfFormatado();
    }
}
