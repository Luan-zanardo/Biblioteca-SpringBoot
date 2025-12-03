package com.biblioteca.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exemplar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @ManyToOne
    private Livro livro;

    private String status; // DISPONIVEL, EMPRESTADO

    private String localizacao;

    public Exemplar(Long id) { this.id = id; }
}
