package com.example;

import com.example.dao.AutomovelDao;
import com.example.dao.DaoFactory;
import com.example.domain.Automovel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Teste {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AutomovelDao automovelDao = DaoFactory.createAutomovelDao();

        System.out.println("teste 1 ");
        Automovel automovel = new Automovel("Tesla", "model x", "AAA1234", sdf.parse("20/10/2005"));
        automovelDao.insert(automovel);

        System.out.println("Teste 2");
        System.out.println(automovelDao.findById(1));


    }
}
