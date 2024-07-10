package com.alurachallenge.Foro_hub.domain.respuesta;

import com.alurachallenge.Foro_hub.domain.respuesta.validaciones.IValidadorDeRespuestas;
import com.alurachallenge.Foro_hub.domain.topico.DatosTopico;
import com.alurachallenge.Foro_hub.domain.topico.Topico;
import com.alurachallenge.Foro_hub.domain.topico.TopicoRepository;
import com.alurachallenge.Foro_hub.domain.usuario.Usuario;
import com.alurachallenge.Foro_hub.domain.usuario.UsuarioRepository;
import com.alurachallenge.Foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    List<IValidadorDeRespuestas> iValidadorDeRespuestas;


    public DatosListarRespuesta registrar(DatosRespuesta datosRespuesta) {
        iValidadorDeRespuestas.forEach(v -> v.validar(datosRespuesta));
        Topico topico = topicoRepository.findById(datosRespuesta.idTopico()).get();
        Usuario usuario = usuarioRepository.findById(datosRespuesta.idUsuario()).get();
        Respuesta respuesta = new Respuesta(
                datosRespuesta.mensaje(),
                datosRespuesta.solucion(),
                topico,
                usuario);
        respuestaRepository.save(respuesta);
        return new DatosListarRespuesta(respuesta);
    }

    public void validarRespuesta(Long id) {
        if (!respuestaRepository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("Respuesta no encontrada");
        }
    }

    public Object actualizar(DatosActualizarRespuesta datosActualizarRespuesta) {
        validarRespuesta(datosActualizarRespuesta.id());
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.setMensaje(datosActualizarRespuesta.mensaje());
        respuesta.setSolucion(datosActualizarRespuesta.solucion());
        return new DatosListarRespuesta(respuesta);
    }
}
