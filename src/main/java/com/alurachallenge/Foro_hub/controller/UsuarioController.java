package com.alurachallenge.Foro_hub.controller;

import com.alurachallenge.Foro_hub.domain.usuario.*;
import com.alurachallenge.Foro_hub.domain.usuario.validaciones.IValidadorDeUsuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios")
@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Crea un usuario.")
    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario) {
        var usuario = usuarioService.registrar(datosUsuario);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Lista todos los usuarios.")
    @GetMapping
    public ResponseEntity<Page<DatosListarUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable painacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(painacion).map(DatosListarUsuario::new));
    }

    @Operation(summary = "Atualiza un usuario.")
    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario actualizarDatosUsuario) {
        var usuario = usuarioService.actualizar(actualizarDatosUsuario);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Elimina un usuario.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(usuarioRepository.getReferenceById(id).getId());
        return ResponseEntity.noContent().build();
    }
}
