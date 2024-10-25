package com.example.controllers;

import com.example.dto.UsuarioRequest;
import com.example.dto.UsuarioResponse;
import com.example.exceptions.LoginNotCreate;
import com.example.exceptions.UsuarioNotCreate;
import com.example.exceptions.UsuarioNotFound;
import com.example.model.Usuario;
import com.example.service.LoginService;
import com.example.service.LoginServiceFactory;
import com.example.service.UsuarioService;
import com.example.service.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Map;

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
        try {
            Usuario usuario = new Usuario(dto.nome(), dto.cpf(), dto.email(), dto.senha());
            loginService.cadastrar(usuario);
            service.cadastraUsuario(usuario);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(usuario.getId())).build();
            return Response.created(uri).entity(transformUsuario(usuario)).build();
        } catch (LoginNotCreate | UsuarioNotCreate e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "dados invalidos")).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") long id) {
        try {
            Usuario usuario = service.buscaUsuarioPorId(id);
            return Response.ok(transformUsuario(usuario)).build();
        } catch (UsuarioNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "usuario não enconstrado")).build();
        }
    }

    private UsuarioResponse transformUsuario(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf());
    }

}
