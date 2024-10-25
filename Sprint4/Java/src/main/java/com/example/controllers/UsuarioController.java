package com.example.controllers;

import com.example.dto.UsuarioRequest;
import com.example.dto.UsuarioResponse;
import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.UsuarioNotCreate;
import com.example.model.Usuario;
import com.example.service.LoginService;
import com.example.service.LoginServiceFactory;
import com.example.service.UsuarioService;
import com.example.service.UsuarioServiceFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/user")
public class UsuarioController {

    private final UsuarioService service = UsuarioServiceFactory.getUsuarioService();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    @Context
    private UriInfo uriInfo;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(UsuarioRequest dto) {
        Usuario usuario = new Usuario(dto.nome(), dto.cpf(), dto.email(), dto.senha());
        try {
            loginService.cadastrar(usuario);
            service.cadastraUsuario(usuario);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(usuario.getId())).build();
            return Response.created(uri).entity(new UsuarioResponse(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf())).build();
        } catch (LoginNotCreate | UsuarioNotCreate e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }



}
