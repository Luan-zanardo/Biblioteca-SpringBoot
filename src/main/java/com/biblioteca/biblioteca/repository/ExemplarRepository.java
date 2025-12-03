package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    List<Exemplar> findByLivroIdAndStatus(Long livroId, String status);
    List<Exemplar> findByLivroId(Long livroId);
}
