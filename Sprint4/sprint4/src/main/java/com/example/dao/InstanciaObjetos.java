package com.example.dao;

import com.example.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

final class InstanciaObjetos {

    public static Usuario instaciaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(rs.getString("nm_nome"), rs.getString("sq_cpf"), rs.getString("nm_email"), rs.getString("sq_senha"));
        usuario.setId(rs.getLong("cd_pessoa"));
        return usuario;
    }

    public static Automovel instanciaAutomovel(ResultSet rs) throws SQLException {
        Automovel automovel = new Automovel(rs.getString("nm_marca_veiculo"), rs.getString("nm_modelo_veiculo"), rs.getString("sq_placa"), rs.getDate("dt_veiculo").toLocalDate(), instaciaUsuario(rs));
        automovel.setId(rs.getLong("cd_automovel"));
        return automovel;
    }

    public static Diagnostico instanciaDiagnostico(ResultSet rs) throws SQLException {
        Diagnostico diagnostico = new Diagnostico(rs.getString("nm_descricao_diagnostico"), rs.getDate("dt_inicio_diagnostico").toLocalDate(), VerificaDiagnostico.getDiagnostico(rs.getString("st_diagnostico")));
        diagnostico.setId(rs.getLong("cd_diagnostico"));
        diagnostico.setOficina(instaciaOficina(rs));
        diagnostico.setAutomovel(instanciaAutomovel(rs));
        if(rs.getDate("dt_fim_diagnostico") != null){
            diagnostico.setDataFinalizado(rs.getDate("dt_fim_diagnostico").toLocalDate());
        }
        return diagnostico;
    }

    public static Oficina instaciaOficina(ResultSet rs) throws SQLException {
        Oficina oficina = new Oficina(rs.getString("nm_nome"), rs.getString("sq_cnpj"), rs.getString("nm_email"), rs.getString("sq_senha"), rs.getString("sq_inscricao_estadual"));
        oficina.setId(rs.getLong("cd_oficina"));
        return oficina;
    }

    public static Endereco instanciaEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco(rs.getString("nm_rua")
                , rs.getInt("nr_casa")
                , rs.getString("sq_cep")
                , rs.getString("nm_bairro")
                , rs.getString("nm_cidade")
                ,rs.getString("nm_estado")
        );
        endereco.setId(rs.getLong("cd_endereco"));
        endereco.setLogin(rs.getLong("cd_pessoa"));

        return endereco;
    }

    public static Orcamento instanciaOrcamento(ResultSet rs) throws SQLException {
        Orcamento orcamento = new Orcamento(StatusOrcamento.getStatus(rs.getString("st_orcamento")), InstanciaObjetos.instanciaDiagnostico(rs));
        orcamento.setId(rs.getLong("cd_orcamento"));
        return orcamento;
    }

    public static ItensOrcamento instanciaItensOrcamento(ResultSet rs) throws SQLException {
        ItensOrcamento itensOrcamento = new ItensOrcamento(
                rs.getInt("qt_pedido")
        );
        itensOrcamento.getId().setProduto(instanciaProduto(rs));
        itensOrcamento.getId().setOrcamento(instanciaOrcamento(rs));
        return itensOrcamento;
    }

    public static Produto instanciaProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto(
                rs.getString("nm_produto"),
                rs.getString("ds_produto"),
                rs.getDouble("vl_produto")
        );
        produto.setId(rs.getLong("cd_produto"));
        return produto;
    }

}
