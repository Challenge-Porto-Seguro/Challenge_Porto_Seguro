package com.example.dao;

import com.example.db.DB;
import com.example.impl.AutomovelDaoJDBC;

public class DaoFactory {

    public static AutomovelDao createAutomovelDao() {
        return new AutomovelDaoJDBC(DB.getConnection());
    }
}
