package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entity.Emprestimo;
import com.biblioteca.biblioteca.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping("/emprestar")
    public Emprestimo emprestar(@RequestParam Long livroId, @RequestParam Long usuarioId) {
        return emprestimoService.emprestar(livroId, usuarioId);
    }

    @PostMapping("/devolver/{id}")
    public Emprestimo devolver(@PathVariable Long id) {
        return emprestimoService.devolver(id);
    }
}
