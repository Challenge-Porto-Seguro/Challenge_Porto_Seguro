package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        if(!validaCpf(cpf)){
            throw new RuntimeException("CPF invalido");
        }
    }

    private boolean validaCpf(String cpf){
        if(cpf.contains(".") || cpf.contains("-")){
            cpf = desformatarCpf(cpf);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf1 = (Cpf) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    @Override
    public String toString() {
        return cpf;
    }
}
