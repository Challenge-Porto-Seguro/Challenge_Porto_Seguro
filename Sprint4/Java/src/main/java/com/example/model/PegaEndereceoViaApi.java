package com.example.model;

import com.example.validacoes.VerificaCepValido;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PegaEndereceoViaApi {

    public static Endereco pegarEndereco(String cep){
        VerificaCepValido.verificaCep(cep);
        cep = cep.replace("-", "");
        try {
            String enderecoPesquisa = "http://viacep.com.br/ws/" + cep + "/json/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoPesquisa))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new Gson();

            return gson.fromJson(json, Endereco.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
