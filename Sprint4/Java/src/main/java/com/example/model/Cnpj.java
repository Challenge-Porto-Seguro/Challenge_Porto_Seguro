package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cnpj {

    private String cnpj;

    public Cnpj(String cnpj) {
        verificaSeCnpjEValido(cnpj);
        this.cnpj = desformataCnpj(cnpj);
    }

    private String desformataCnpj(String cnpj){
        return cnpj.replaceAll("[-./]", "");
    }

    public void verificaSeCnpjEValido(String cnpj){
        if(!validaCnpj(cnpj)){
            throw new RuntimeException("Cnpj invalido");
        }
    }

    private boolean validaCnpj(String cnpj){
        cnpj = desformataCnpj(cnpj);
        if(cnpj.length() != 14){
            return false;
        }
        List<Integer> digitos = new ArrayList<>(Arrays.stream(cnpj.split("")).map(Integer::parseInt).toList());
        List<Integer> digitosVerificadores = new ArrayList<>(Arrays.asList(digitos.remove(12), digitos.remove(12)));

        List<Integer> digitoAchado = new ArrayList<>();

        int multiplicador = 5;
        int sum = 0;
        for(int digito : digitos){
            sum += digito * multiplicador;
            if(multiplicador == 2){
                multiplicador = 9;
            } else {
                multiplicador--;
            }
        }

        int restoDivisao = sum % 11;

        if(restoDivisao < 2){
            digitoAchado.add(0);
        } else {
            digitoAchado.add(11 - restoDivisao);
        }

        digitos.add(digitoAchado.get(0));

        multiplicador = 6;
        sum = 0;
        for (int digito : digitos){
            sum += digito * multiplicador;
            if(multiplicador == 2){
                multiplicador = 9;
            } else {
                multiplicador--;
            }
        }

        restoDivisao = sum % 11;

        if(restoDivisao < 2){
            digitoAchado.add(0);
        } else {
            digitoAchado.add(11 - restoDivisao);
        }

        return digitoAchado.equals(digitosVerificadores);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj1 = (Cnpj) o;
        return Objects.equals(cnpj, cnpj1.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpj);
    }

    @Override
    public String toString() {
        return cnpj;
    }
}
