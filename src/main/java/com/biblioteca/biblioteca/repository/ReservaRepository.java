package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByLivroIdAndStatusOrderByPosicao(Long livroId, String status);
    List<Reserva> findByLivroIdAndStatus(Long livroId, String status);
}
