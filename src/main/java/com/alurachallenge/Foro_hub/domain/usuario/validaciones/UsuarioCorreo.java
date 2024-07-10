package com.alurachallenge.Foro_hub.domain.usuario.validaciones;

import com.alurachallenge.Foro_hub.domain.usuario.DatosUsuario;
import com.alurachallenge.Foro_hub.domain.usuario.UsuarioRepository;
import com.alurachallenge.Foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCorreo implements IValidadorDeUsuarios {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(DatosUsuario datosUsuario) {
        var usuario = usuarioRepository.existsByCorreo(datosUsuario.correo());
        if (usuario) {
            throw new ValidacionDeIntegridad("Correo ya registrado");
        }
    }
}
