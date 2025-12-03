package com.biblioteca.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exemplar exemplar;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;

    private LocalDateTime dataDevolucao;

    private String status; // ABERTO, DEVOLVIDO

    public Emprestimo(Long id) { this.id = id; }
}
