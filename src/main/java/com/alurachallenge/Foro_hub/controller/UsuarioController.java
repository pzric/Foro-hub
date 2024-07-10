package com.alurachallenge.Foro_hub.controller;

import com.alurachallenge.Foro_hub.domain.usuario.*;
import com.alurachallenge.Foro_hub.domain.usuario.validaciones.IValidadorDeUsuarios;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    List<IValidadorDeUsuarios> validadorDeUsuarios;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario) {
        validadorDeUsuarios.forEach(v -> v.validar(datosUsuario));
        var usuario = new Usuario(datosUsuario, passwordEncoder);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable painacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(painacion).map(DatosListarUsuario::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarDatosUsuario actualizarDatosUsuario) {
        validadorDeUsuarios.forEach(v -> v.validar(new DatosUsuario(
                actualizarDatosUsuario.nombre(),
                actualizarDatosUsuario.correo(),
                actualizarDatosUsuario.contrasena(),
                actualizarDatosUsuario.rol())));
        var usuario = usuarioRepository.getReferenceById(actualizarDatosUsuario.id());
        usuario.actualizarUsuario(actualizarDatosUsuario, passwordEncoder);
        return ResponseEntity.ok(new DatosListarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        var datosUsuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.deleteById(datosUsuario.getId());
        return ResponseEntity.noContent().build();
    }
}
