package com.example.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        digitos.add(digitoAchado.getFirst());

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

    private String formataCnpj(){
        StringBuilder sb = new StringBuilder();
        sb.append(cnpj, 0, 2);
        sb.append(".");
        sb.append(cnpj, 2, 5);
        sb.append(".");
        sb.append(cnpj, 5, 8);
        sb.append("/");
        sb.append(cnpj, 8, 12);
        sb.append("-");
        sb.append(cnpj, 12, 14);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Cnpj: " + formataCnpj();
    }
}
