package com.example.config;

final class DatabaseConfig {

    private DatabaseConfig() {
        throw new UnsupportedOperationException("DatabaseConfig não pode ser instanciada");
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    }

    static String getUser(){
        return System.getenv("DB_USER_ORACLE");
    }

    static String getPassword(){
        return System.getenv("DB_PASSWORD_ORACLE");
    }
}
