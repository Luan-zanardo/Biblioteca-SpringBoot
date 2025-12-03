package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entity.Reserva;
import com.biblioteca.biblioteca.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping("/criar")
    public Reserva criar(@RequestParam Long livroId, @RequestParam Long usuarioId) {
        return reservaService.criarReserva(livroId, usuarioId);
    }
}