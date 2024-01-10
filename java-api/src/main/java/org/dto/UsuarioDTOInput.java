package org.dto;

import lombok.Data;

@Data
public class UsuarioDTOInput {
    private int id;
    private String nome;
    private String senha;
}
