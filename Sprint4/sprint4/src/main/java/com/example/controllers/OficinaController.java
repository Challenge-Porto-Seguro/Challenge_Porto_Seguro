package com.example.controllers;

import com.example.dto.OficinaRequest;
import com.example.dto.OficinaResponse;
import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Oficina;
import com.example.service.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/oficina")
public class OficinaController {

    private final OficinaService oficinaService = OficinaServiceFactory.getOficinaService();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    private final EnderecoService enderecoService = EnderecoServiceFactory.getEnderecoService();
    @Context
    private UriInfo uriInfo;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOficina(OficinaRequest dto) {
        try {
            Oficina oficina = new Oficina(dto.nome(), dto.cnpj(), dto.email(), dto.senha(), dto.inscricaoEstadual());
            Endereco endereco = new Endereco(dto.cep(), dto.numero());
            oficinaService.cadastraOficina(oficina, endereco);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(oficina.getId())).build();
            return Response.created(uri).entity(transformOficina(oficina, endereco)).build();
        }catch (RuntimeException | CadastroInvalido e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", e.getMessage())).build();
        } catch (LoginNotCreate | OficinaNotCreate | EnderecoNotCreate | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOficinaById(@PathParam("id") long id) {
        try {
            Oficina oficina = oficinaService.buscaOficinaPorId(id);
            Endereco endereco = enderecoService.buscaEnderecoPorId(oficina.getId());
            return Response.ok(transformOficina(oficina, endereco)).build();
        } catch (OficinaNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "Oficina não enconstrado")).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (EnderecoNotFound e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "Endereco não enconstrado")).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOficinas() {
        try {
            List<Oficina> oficinas = oficinaService.listaOficinas();
            List<OficinaResponse> oficinaResponses = new ArrayList<>();
            for (Oficina oficina : oficinas) {
                oficinaResponses.add(transformOficina(oficina, enderecoService.buscaEnderecoPorId(oficina.getId())));
            }
            return Response.ok(oficinaResponses).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (EnderecoNotFound e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "Endereco não enconstrado")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOficina(@PathParam("id") Long id, OficinaRequest dto) {
        try {
            Oficina oficina = new Oficina(dto.nome(), dto.senha(), dto.inscricaoEstadual(), dto.cnpj());
            oficina.setId(id);
            Endereco endereco = new Endereco(dto.cep(), dto.numero());
            oficina = oficinaService.alteraOficina(oficina, endereco);
            return Response.ok(transformOficina(oficina, endereco)).build();
        } catch (LoginNotUpdade | OficinaNotUpdate | EnderecoNotUpdate | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (OficinaNotFound  e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "oficina não enconstrado")).build();
        }  catch (LoginNotFound  e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "login não enconstrado")).build();
        } catch (EnderecoNotFound e){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", "endereco não enconstrado")).build();
        }
    }

    private OficinaResponse transformOficina(Oficina oficina, Endereco endereco) {
        return new OficinaResponse(oficina.getId(), oficina.getNome(), oficina.getEmail(), oficina.getCnpj(), oficina.getInscricaoEstadual(), oficina.getSenha(),
                endereco.getRua(), endereco.getCidade(), endereco.getUf(), endereco.getBairro(), endereco.getCep(), endereco.getNumero());
    }
}
