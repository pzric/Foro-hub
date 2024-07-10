package com.alurachallenge.Foro_hub.domain.usuario;

import com.alurachallenge.Foro_hub.domain.respuesta.DatosListarRespuesta;

public record DatosListarUsuario(
        String nombre,
        String correo,
        Rol rol) {

    public DatosListarUsuario(Usuario usuario) {
        this(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}
