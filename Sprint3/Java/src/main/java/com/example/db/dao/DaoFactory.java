package com.example.db.dao;

import com.example.db.DB;
import com.example.db.implDao.AutomovelDaoJDBC;

public class DaoFactory {

    public static AutomovelDao createAutomovelDao() {
        return new AutomovelDaoJDBC(DB.getConnection());
    }
}
