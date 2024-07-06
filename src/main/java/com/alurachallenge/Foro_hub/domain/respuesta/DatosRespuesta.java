package com.alurachallenge.Foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DatosRespuesta(
        @NotBlank String mensaje,
        @NotBlank Long idTopico,
        @NotBlank LocalDateTime fechaCreacion,
        @NotBlank Long idUsuario,
        @NotBlank String solucion) {
}
