package com.example.db.dao;

import com.example.db.DB;
import com.example.db.implDao.AutomovelDaoJDBC;
import com.example.db.implDao.UsuarioDaoJDBC;

public class DaoFactory {

    public static AutomovelDao createAutomovelDao() {
        return new AutomovelDaoJDBC(DB.getConnection());
    }

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }
}
