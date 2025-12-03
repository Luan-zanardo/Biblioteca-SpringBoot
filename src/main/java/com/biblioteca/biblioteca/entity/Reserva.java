package com.biblioteca.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime dataReserva;

    private Integer posicao; // fila

    private String status; // ATIVA, ATENDIDA, CANCELADA

    public Reserva(Long id) { this.id = id; }
}
