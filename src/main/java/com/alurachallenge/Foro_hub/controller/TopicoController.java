package com.alurachallenge.Foro_hub.controller;

import com.alurachallenge.Foro_hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosTopico datosTopico) {
        var topico = topicoService.registrar(datosTopico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listarTopicos (@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findAllByOrderByFechaCreacionAsc(pageable).map(DatosListarTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesTopico> listarDetallesTopico(@PathVariable Long id) {
        topicoService.validarTopico(id);
        var datosTopico = new DatosDetallesTopico(topicoRepository.getReferenceById(id));
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var respuesta = topicoService.actualizar(datosActualizarTopico);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoService.validarTopico(id);
        var datosTopico = topicoRepository.getReferenceById(id);
        topicoRepository.deleteById(datosTopico.getId());
        return ResponseEntity.noContent().build();
    }
}
