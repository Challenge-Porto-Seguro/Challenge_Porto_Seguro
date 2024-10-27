package com.example.controllers;


import com.example.dto.AutomovelRequest;
import com.example.dto.AutomovelResponse;
import com.example.exceptions.*;
import com.example.model.Automovel;
import com.example.model.Usuario;
import com.example.service.AutomovelService;
import com.example.service.AutomovelServiceFactory;
import com.example.service.UsuarioService;
import com.example.service.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Path("/automovel")
public class AutomovelController {

    private AutomovelService automovelService = AutomovelServiceFactory.getAutomovelService();
    private UsuarioService usuarioService = UsuarioServiceFactory.getUsuarioService();
    @Context
    private UriInfo uriInfo;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAutomovel(AutomovelRequest request) {
        try {
            Usuario usuario = usuarioService.buscaUsuarioPorId(request.userId());
            Automovel automovel = new Automovel(request.marca(), request.modelo(), request.placa(), LocalDate.parse(request.dataVeiculo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), usuario);
            Automovel newAutomovel = automovelService.cadastraAutomovel(automovel);
            URI uri = uriInfo.getAbsolutePathBuilder().path(newAutomovel.getId().toString()).build();
            return Response.created(uri).entity(transformaAutomovel(newAutomovel)).build();
        } catch (UsuarioNotFound e){
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("mensagem", "Usuario não encontrado")).build();
        }catch (AutomovelNotCreate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("erro", "Não possivel criar automovel")).build();
        } catch (AutomovelInvalido e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("erro", "não foi possivel adicioanar o automovel")).build();
        } catch (RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("erro", e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAutomovel(@PathParam("id") Long id, AutomovelRequest request) {
        try {
            Usuario usuario = usuarioService.buscaUsuarioPorId(request.userId());
            Automovel automovel = new Automovel(request.marca(), request.modelo(), request.placa(), LocalDate.parse(request.dataVeiculo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), usuario);
            automovel.setId(id);
            automovel = automovelService.alteraAutomovel(automovel);
            return Response.ok(transformaAutomovel(automovel)).build();
        } catch (RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("erro", e.getMessage())).build();
        }catch (AutomovelNotUpdate e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("erro", "não foi possivel atualizar o automovel")).build();
        } catch (UsuarioNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("mensagem", "Usuario não encontrado")).build();
        } catch (AutomovelNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("mensagem", "Automovel não encontrado")).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAutomovesByUserId(@QueryParam("userId") Long userId) {
        try {
            List<AutomovelResponse> automovels = automovelService.listaAutomoveis(userId).stream()
                    .map(this::transformaAutomovel)
                    .toList();
            return Response.ok(automovels).build();
        } catch (AutomovelInvalido e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("erro", "erro ao buscar todos automoveis por id")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutomovelById(@PathParam("id") Long id) {
        try {
            Automovel automovel = automovelService.buscaAutomovelPorId(id);
            return Response.ok(transformaAutomovel(automovel)).build();
        } catch (AutomovelNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("mensagem", "Automovel não encontrado")).build();
        }
    }

    private AutomovelResponse transformaAutomovel(Automovel automovel) {
        return new AutomovelResponse(automovel.getId(), automovel.getMarca(), automovel.getModelo(), automovel.getPlaca(), automovel.getAno().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), automovel.getUsuario().getId());
    }

}
