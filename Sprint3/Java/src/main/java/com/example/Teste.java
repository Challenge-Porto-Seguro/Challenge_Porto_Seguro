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

        System.out.println("Teste 2");
        System.out.println(automovelDao.findById(1));

        System.out.println("Teste 3");
        Automovel automovel1 = automovelDao.findById(1).get();
        System.out.println(automovel1);
        automovel1.setMarca("Ola mundo");
        automovelDao.update(automovel1);
        System.out.println(automovelDao.findById(1).get());
    }
}
