package com.example.service;

public final class LoginServiceFactory {

    private LoginServiceFactory() {
        throw new UnsupportedOperationException("Essa classe não deve ser instanciada");
    }

    public static LoginService getLoginService() {
        return new LoginServiceImpl();
    }

}
