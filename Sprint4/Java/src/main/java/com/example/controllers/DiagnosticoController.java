package com.example.controllers;

import com.example.dto.DiagnosticoRequest;
import com.example.dto.DiagnosticoResponse;
import com.example.exceptions.*;
import com.example.model.*;
import com.example.service.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Path("diagnostico")
public class DiagnosticoController {

    private DiagnosticoService diagnosticoService = DiagnosticoServiceFactory.getDiagnosticoService();
    private OficinaService oficinaService = OficinaServiceFactory.getOficinaService();
    private AutomovelService automovelService = AutomovelServiceFactory.getAutomovelService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDiagnostico(DiagnosticoRequest dto) {
        try {
            Oficina oficina = oficinaService.buscaOficinaPorId(dto.cd_oficina());
            Automovel automovel = automovelService.buscaAutomovelPorId(dto.cd_automovel());
            Diagnostico diagnostico = new Diagnostico(dto.descricao(), LocalDate.parse(dto.dt_inicio(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO);
            diagnostico.setAutomovel(automovel);
            diagnostico.setOficina(oficina);
            Diagnostico newDiagnostico = diagnosticoService.insertDiagnostico(diagnostico);
            return Response.ok(getDiagnostico(newDiagnostico)).build();
        }
        catch (DiagnoticoNotCreated | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (OficinaNotFound | AutomovelNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDiagnostico(@PathParam("id") Long id) {
        try {
            Diagnostico diagnostico = new Diagnostico(null, null, null);
            diagnostico.setId(id);
            diagnostico.setDataFinalizado(LocalDate.now());
            diagnostico.setStatus(VerificaDiagnostico.RESOLVIDO);
            Diagnostico newDiagnostico = diagnosticoService.updateDiagnostico(diagnostico);
            return Response.ok(getDiagnostico(newDiagnostico)).build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (DiagnosticoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", e.getMessage())).build();
        }
    }

    @GET
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiagnosticoById(@PathParam("id") Long id) {
        try {
            Diagnostico diagnostico = diagnosticoService.getDiagnosticoById(id);
            return Response.ok(getDiagnostico(diagnostico)).build();
        }catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (DiagnosticoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", e.getMessage())).build();
        }
    }

    @GET
    @Path("/userId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDiagnosticosByUserId(@PathParam("userId") Long userId) {
        try {
            List<DiagnosticoResponse> diagnosticos = diagnosticoService.getAllDiagnosticosByCdPessoa(userId)
                    .stream()
                    .map(this::getDiagnostico)
                    .toList();
            return Response.ok(diagnosticos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        }
    }

    private DiagnosticoResponse getDiagnostico(Diagnostico diagnostico) {
        return new DiagnosticoResponse(diagnostico.getAutomovel().getId(), diagnostico.getDescricao(), diagnostico.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                diagnostico.getOficina().getId(), diagnostico.getDataFinalizado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), diagnostico.getStatus().toString());
    }

}
