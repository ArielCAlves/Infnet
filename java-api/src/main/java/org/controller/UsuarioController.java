package org.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import spark.Spark;
import org.dto.UsuarioDTOInput;
import org.dto.UsuarioDTOOutput;
import org.service.UsuarioService;
import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void respostasRequisicoes() {
        // System.out.println("Método respostasRequisicoes chamado.");
        Spark.get("/usuarios/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params(":id"));
            UsuarioDTOOutput usuarioDTOOutput = usuarioService.buscar(userId);
            if (usuarioDTOOutput != null) {
                res.status(200);
                return objectMapper.writeValueAsString(usuarioDTOOutput);
            } else {
                res.status(404);
                return "Usuário não encontrado";
            }
        });

        Spark.get("/usuarios", (req, res) -> {
            List<UsuarioDTOOutput> usuariosDTO = usuarioService.listar();
            res.status(200);
            return objectMapper.writeValueAsString(usuariosDTO);
        });

        Spark.delete("/usuarios/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params(":id"));
            usuarioService.excluir(userId);
            res.status(204);
            return "";
        });

        Spark.post("/usuarios", (req, res) -> {
            try {
                UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
                usuarioService.inserir(usuarioDTOInput);
                res.status(201);
                return "Usuário inserido com sucesso";
            } catch (JsonMappingException e) {
                res.status(400);
                return "Falha na conversão do JSON";
            }
        });

        Spark.put("/usuarios", (req, res) -> {
            try {
                UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
                usuarioService.alterar(usuarioDTOInput);
                res.status(200);
                return "Usuário atualizado com sucesso";
            } catch (JsonMappingException e) {
                res.status(400);
                return "Falha na conversão do JSON";
            }
        });
    }
}
