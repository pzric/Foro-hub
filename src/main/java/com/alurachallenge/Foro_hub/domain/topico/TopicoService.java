package com.alurachallenge.Foro_hub.domain.topico;

import com.alurachallenge.Foro_hub.domain.curso.Curso;
import com.alurachallenge.Foro_hub.domain.curso.CursoRepository;
import com.alurachallenge.Foro_hub.domain.topico.validaciones.IValidadorDeTopicos;
import com.alurachallenge.Foro_hub.domain.usuario.Usuario;
import com.alurachallenge.Foro_hub.domain.usuario.UsuarioRepository;
import com.alurachallenge.Foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    List<IValidadorDeTopicos> iValidadorDeTopicos;

    public DatosListarTopico registrar(DatosTopico datosTopico) {
       iValidadorDeTopicos.forEach(v -> v.validar(datosTopico));
        Usuario usuario = usuarioRepository.findById(datosTopico.idUsuario()).get();
        Curso curso = cursoRepository.findById(datosTopico.idCurso()).get();
        Topico topico = new Topico(
                datosTopico.titulo(),
                datosTopico.mensaje(),
                usuario,
                curso);
        topicoRepository.save(topico);
        return new DatosListarTopico(topico);
    }

    public void validarTopico(Long id) {
        if (!topicoRepository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }

    public DatosListarTopico actualizar(DatosActualizarTopico datosActualizarTopico) {
        validarTopico(datosActualizarTopico.id());
        iValidadorDeTopicos.forEach(v -> v.validar(new DatosTopico(
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(),
                datosActualizarTopico.idUsuario(),
                datosActualizarTopico.idCurso())));
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.setTitulo(datosActualizarTopico.titulo());
        topico.setMensaje(datosActualizarTopico.mensaje());
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarTopico.idUsuario());
        topico.setUsuario(usuario);
        Curso curso = cursoRepository.getReferenceById(datosActualizarTopico.idCurso());
        topico.setCurso(curso);
        return new DatosListarTopico(topico);
    }
}
