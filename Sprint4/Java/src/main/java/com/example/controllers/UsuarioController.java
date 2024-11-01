package com.example.controllers;

import com.example.dto.UsuarioRequest;
import com.example.dto.UsuarioResponse;
import com.example.dto.UsuarioUpdateRequest;
import com.example.exceptions.*;
import com.example.model.Endereco;
import com.example.model.Usuario;
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

@Path("/user")
public class UsuarioController {

    private final UsuarioService service = UsuarioServiceFactory.getUsuarioService();
    private final LoginService loginService = LoginServiceFactory.getLoginService();
    private final EnderecoService enderecoService = EnderecoServiceFactory.getEnderecoService();
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
            Endereco endereco = new Endereco(dto.cep(), dto.numero());
            endereco.setLogin(usuario.getId());
            enderecoService.cadastraEndereco(endereco);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(usuario.getId())).build();
            return Response.created(uri).entity(transformUsuario(usuario, endereco)).build();
        }catch (RuntimeException | CadastroInvalido e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("message", e.getMessage())).build();
        } catch (LoginNotCreate | UsuarioNotCreate | EnderecoNotCreate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") long id) {
        try {
            Usuario usuario = service.buscaUsuarioPorId(id);
            Endereco endereco = enderecoService.buscaEnderecoPorId(usuario.getId());
            return Response.ok(transformUsuario(usuario, endereco)).build();
        } catch (UsuarioNotFound | EnderecoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "usuario não enconstrado")).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        try {
            List<Usuario> usuarios = service.listaUsuarios();
            List<UsuarioResponse> usuarioResponses = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                usuarioResponses.add(transformUsuario(usuario, enderecoService.buscaEnderecoPorId(usuario.getId())));
            }
            return Response.ok(usuarios).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (EnderecoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "endereco não enconstrado")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") Long id, UsuarioUpdateRequest dto) {
        try {
            Usuario usuario = new Usuario(dto.nome(), dto.senha(), dto.cpf());
            usuario.setId(id);
            loginService.update(usuario);
            service.alteraUsuario(usuario);
            Endereco endereco = new Endereco(dto.cep(), dto.numero());
            endereco.setLogin(usuario.getId());
            enderecoService.updateEndereco(endereco);
            return Response.ok(transformUsuario(usuario, endereco)).build();
        } catch (LoginNotUpdade | UsuarioNotUpdate | EnderecoNotFound | EnderecoNotUpdate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", e.getMessage())).build();
        } catch (UsuarioNotFound | LoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", "usuario não enconstrado")).build();
        }
    }

    private UsuarioResponse transformUsuario(Usuario usuario, Endereco endereco) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getSenha(),
                endereco.getRua(), endereco.getCidade(), endereco.getUf(), endereco.getBairro(), endereco.getCep(), endereco.getNumero());
    }

}
