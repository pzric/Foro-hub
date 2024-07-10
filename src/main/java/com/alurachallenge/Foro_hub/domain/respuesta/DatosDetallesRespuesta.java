package com.alurachallenge.Foro_hub.domain.respuesta;

import com.alurachallenge.Foro_hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetallesRespuesta(
        String mensaje,
        String solucion,
        LocalDateTime fechaCreacion,
        Long usuario) {


    public DatosDetallesRespuesta(Respuesta respuesta) {
        this(
                respuesta.getMensaje(),
                respuesta.getSolucion(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getId()
        );
    }
}
