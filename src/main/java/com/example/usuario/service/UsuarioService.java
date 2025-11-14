package com.example.usuario.service;

import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
