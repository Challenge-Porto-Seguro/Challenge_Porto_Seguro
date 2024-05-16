package com.example.domain;

import com.example.ValidaInformacoesMinimoInformacao;

import java.util.ArrayList;
import java.util.Arrays;
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
        if(!validaCPF(cpf)){
            throw new RuntimeException("CPF invalido");
        }
    }

    private boolean validaCPF(String cpf){
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
