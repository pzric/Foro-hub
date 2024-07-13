package com.alurachallenge.Foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRespuesta(
        @NotBlank String mensaje,
        @NotNull Long idTopico,
        @NotNull Long idUsuario) {
}
