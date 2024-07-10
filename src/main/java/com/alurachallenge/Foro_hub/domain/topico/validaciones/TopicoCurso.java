package com.alurachallenge.Foro_hub.domain.topico.validaciones;

import com.alurachallenge.Foro_hub.domain.curso.CursoRepository;
import com.alurachallenge.Foro_hub.domain.topico.DatosTopico;
import com.alurachallenge.Foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoCurso implements IValidadorDeTopicos {
    @Autowired
    CursoRepository cursoRepository;

    public void validar(DatosTopico datosTopico) {
        if (!cursoRepository.findById(datosTopico.idCurso()).isPresent()) {
            throw new ValidacionDeIntegridad("Curso no encontrado");
        }
    }
}
