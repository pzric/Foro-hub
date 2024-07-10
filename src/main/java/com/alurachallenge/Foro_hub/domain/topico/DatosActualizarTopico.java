package com.alurachallenge.Foro_hub.domain.topico;

import com.alurachallenge.Foro_hub.domain.curso.Curso;
import com.alurachallenge.Foro_hub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long idUsuario,
        @NotNull Long idCurso) {
}
