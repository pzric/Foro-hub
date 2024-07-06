package com.alurachallenge.Foro_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotBlank String nombre,
        @NotNull Categoria categoria) {
}
