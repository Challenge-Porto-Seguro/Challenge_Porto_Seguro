package com.example.controllers;

import com.example.dto.OficinaRequest;
import com.example.dto.OficinaResponse;
import com.example.exceptions.*;
import com.example.model.Oficina;
import com.example.service.LoginService;
import com.example.service.LoginServiceFactory;
import com.example.service.OficinaService;
import com.example.service.OficinaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/oficina")
public class OficinaController {

    private OficinaService oficinaService = OficinaServiceFactory.getOficinaService();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    @Context
    private UriInfo uriInfo;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(OficinaRequest dto) {
        try {
            Oficina oficina = new Oficina(dto.nome(), dto.cnpj(), dto.email(), dto.senha(), dto.inscricaoEstadual());
            loginService.cadastrar(oficina);
            oficinaService.cadastraOficina(oficina);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(oficina.getId())).build();
            return Response.created(uri).entity(transformOficina(oficina)).build();
        }catch (RuntimeException | CadastroInvalido e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", e.getMessage())).build();
        } catch (LoginNotCreate | OficinaNotCreate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") long id) {
        try {
            Oficina oficina = oficinaService.buscaOficinaPorId(id);
            return Response.ok(transformOficina(oficina)).build();
        } catch (OficinaNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "Oficina não enconstrado")).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        try {
            List<OficinaResponse> usuarios = oficinaService.listaOficinas().stream().map(this::transformOficina).toList();
            return Response.ok(usuarios).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") Long id, OficinaRequest dto) {
        try {
            Oficina oficina = new Oficina(dto.nome(), dto.senha(), dto.inscricaoEstadual(), dto.cnpj());
            oficina.setId(id);
            loginService.update(oficina);
            oficinaService.alteraOficina(oficina);
            return Response.ok(transformOficina(oficina)).build();
        } catch (LoginNotUpdade | OficinaNotUpdate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (OficinaNotFound | LoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "usuario não enconstrado")).build();
        }
    }

    private OficinaResponse transformOficina(Oficina oficina) {
        return new OficinaResponse(oficina.getId(), oficina.getNome(), oficina.getEmail(), oficina.getCnpj(), oficina.getInscricaoEstadual(), oficina.getSenha());
    }
}
