package org.model;

import static spark.Spark.*;
import org.controller.UsuarioController;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Iniciou Spark");
        port(4567);  // Porta que o Spark vai escutar

        UsuarioController usuarioController = new UsuarioController();
        usuarioController.respostasRequisicoes();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Recurso não encontrado\"}";
        });

        awaitInitialization();
        System.out.println("Servidor iniciado na porta 4567");
    }
}

