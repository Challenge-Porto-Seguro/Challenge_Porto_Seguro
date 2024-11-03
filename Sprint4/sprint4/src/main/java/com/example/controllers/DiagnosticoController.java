package com.example.controllers;

import com.example.dto.DiagnosticoByIdResponse;
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
            Orcamento orcamento = new Orcamento(StatusOrcamento.ATIVO, diagnostico);
            Produto produto = new Produto(dto.peca(), null, dto.preco());
            ItensOrcamento itensOrcamento = new ItensOrcamento(dto.quantidade());
            Diagnostico newDiagnostico = diagnosticoService.insertDiagnostico(diagnostico, orcamento, produto, itensOrcamento);
            return Response.ok(getDiagnostico(newDiagnostico)).build();
        } catch (MaximoDiagnosticoException e) {
            return Response.status(Response.Status.TOO_MANY_REQUESTS).entity(Map.of("error", e.getMessage())).build();
        } catch (DiagnoticoNotCreated | SQLException | ProdutoNotCreate e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (OficinaNotFound | AutomovelNotFound | ProdutoNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", "não encontrado")).build();
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
        } catch (DiagnosticoNotFound | OrcamentoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Não encontrado")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiagnosticoById(@PathParam("id") Long id) {
        try {
            DiagnosticoByIdResponse diagnostico = diagnosticoService.getDiagnosticoById(id);
            return Response.ok(diagnostico).build();
        }catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (DiagnosticoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Diagnostico não encotrado")).build();
        } catch (OrcamentoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Orçamento não encotrado")).build();
        } catch (ProdutoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Produto não encotrado")).build();
        }
    }

    @GET
    @Path("/buscar_todos/{userId}")
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

    @DELETE
    @Path("/{id}")
    public Response deleteDiagnostico(@PathParam("id") Long id) {
        try {
            diagnosticoService.deleteDiagnostico(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (OrcamentoNotFound  e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "orcamento não encontrado")).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        } catch (DiagnosticoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "diagnostico não encontrado")).build();
        } catch (ItensOrcamentoNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "itens orcamento não encontrado")).build();
        }
    }

    private DiagnosticoResponse getDiagnostico(Diagnostico diagnostico) {
        String date;
        try {
            date = diagnostico.getDataFinalizado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (RuntimeException e) {
            date = null;
        }
        return new DiagnosticoResponse(diagnostico.getId(), diagnostico.getAutomovel().getId(), diagnostico.getAutomovel().getMarca(), diagnostico.getDescricao(), diagnostico.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                diagnostico.getOficina().getId(), diagnostico.getOficina().getNome(), date , diagnostico.getStatus().toString());
    }
}
