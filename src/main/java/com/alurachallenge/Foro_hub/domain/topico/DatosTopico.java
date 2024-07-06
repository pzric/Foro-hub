package com.alurachallenge.Foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank LocalDateTime fechaCreacion,
        @NotNull Long idUsuario,
        @NotNull Long idCurso) {
}
