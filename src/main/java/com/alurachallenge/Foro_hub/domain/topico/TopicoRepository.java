package com.alurachallenge.Foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByOrderByFechaCreacionAsc(Pageable paginacion);

    @Query("SELECT t FROM Topico t WHERE t.titulo=:titulo AND t.mensaje=:mensaje")
    List<Topico> findTopicoDuplicado(String titulo, String mensaje);
}
