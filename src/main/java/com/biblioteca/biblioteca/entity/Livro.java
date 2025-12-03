package com.biblioteca.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private Integer ano;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Categoria categoria;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    public Livro(Long id) { this.id = id; }
}
