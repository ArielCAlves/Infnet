package org.service;


import org.dto.UsuarioDTOInput;
import org.dto.UsuarioDTOOutput;
import org.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final List<Usuario> listaUsuarios = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {
        List<UsuarioDTOOutput> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            usuariosDTO.add(modelMapper.map(usuario, UsuarioDTOOutput.class));
        }
        return usuariosDTO;
    }

    public void inserir(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterar(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        Optional<Usuario> usuarioExistente = listaUsuarios.stream()
                .filter(u -> u.getId() == usuario.getId())
                .findFirst();
        usuarioExistente.ifPresent(u -> {
            listaUsuarios.remove(u);
            listaUsuarios.add(usuario);
        });
    }

    public UsuarioDTOOutput buscar(int id) {
        Optional<Usuario> usuarioExistente = listaUsuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return usuarioExistente.map(u -> modelMapper.map(u, UsuarioDTOOutput.class)).orElse(null);
    }

    public void excluir(int id) {
        listaUsuarios.removeIf(u -> u.getId() == id);
    }
}

