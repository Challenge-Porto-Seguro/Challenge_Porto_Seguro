package com.example.controllers;

import com.example.dto.LogarResponse;
import com.example.dto.LoginRequest;
import com.example.dto.UsuarioResponse;
import com.example.exceptions.ErroLogar;
import com.example.exceptions.LoginNotFound;
import com.example.exceptions.UsuarioNotFound;
import com.example.model.Oficina;
import com.example.service.*;

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
    private OficinaService oficinaService = OficinaServiceFactory.getOficinaService();

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logar(LoginRequest dto) {
        try {
            Long id = loginService.login(dto.email(), dto.senha());
            try {
                oficinaService.buscaOficinaPorId(id);
            } catch (Exception e) {
                return Response.ok(new LogarResponse(id, "USUARIO")).build();
            }
            return Response.ok(new LogarResponse(id, "OFICINA")).build();
        } catch (LoginNotFound e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "email ou senha invalido")).build();
        } catch (ErroLogar e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        }
    }
}
