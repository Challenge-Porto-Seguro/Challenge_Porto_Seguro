package com.example.controllers;

import com.example.dto.LoginRequest;
import com.example.exceptions.ErroLogar;
import com.example.exceptions.LoginNotFound;
import com.example.service.LoginService;
import com.example.service.LoginServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/login")
public class LoginController {

    private LoginService loginService = LoginServiceFactory.getLoginService();

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logar(LoginRequest dto) {
        try {
            return Response.ok(loginService.login(dto.email(), dto.senha())).build();
        } catch (LoginNotFound e){
            return Response.status(Response.Status.OK).entity(Map.of("message", "email ou senha invalido")).build();
        } catch (ErroLogar e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        }
    }
}
