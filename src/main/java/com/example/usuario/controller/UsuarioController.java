package com.example.usuario.controller;

import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/usuario", produces = "application/json")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<Page<Usuario>> listarTodos(Pageable pageable){
        try
        {
            return new ResponseEntity<>(usuarioService.findAll(pageable), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid Usuario usuario){
        try {
            usuarioService.save(usuario);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable long id){
        try
        {
            usuarioService.remover(id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable(value="id") Long id, @RequestBody @Valid Usuario usuario) {
        Optional<Usuario> us = usuarioService.findById(id);
        try
        {
            usuario.setUsername(us.get().getUsername());
            usuario.setSenha(us.get().getSenha());
            usuarioService.save(usuario);
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
