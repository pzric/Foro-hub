package com.alurachallenge.Foro_hub.domain.topico.validaciones;

import com.alurachallenge.Foro_hub.domain.topico.DatosTopico;
import com.alurachallenge.Foro_hub.domain.usuario.UsuarioRepository;
import com.alurachallenge.Foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoUsuario implements IValidadorDeTopicos {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosTopico datosTopico){
        if (!usuarioRepository.findById(datosTopico.idUsuario()).isPresent()) {
            throw new ValidacionDeIntegridad("Usuario no encontrado");
        }
    }
}
