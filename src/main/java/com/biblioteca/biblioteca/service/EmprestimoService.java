package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entity.*;
import com.biblioteca.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.biblioteca.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final ExemplarRepository exemplarRepo;
    private final UsuarioRepository usuarioRepo;
    private final EmprestimoRepository emprestimoRepo;
    private final ReservaRepository reservaRepo;

    private static final int PRAZO_DIAS = 7;
    private static final int LIMITE_POR_USUARIO = 5;

    @Transactional
    public Emprestimo emprestar(Long livroId, Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // verifica limite
        long emprestimosAbertos = emprestimoRepo.findByUsuarioId(usuarioId).stream()
                .filter(e -> "ABERTO".equals(e.getStatus())).count();
        if (emprestimosAbertos >= LIMITE_POR_USUARIO) {
            throw new RuntimeException("Usuário atingiu limite de empréstimos");
        }

        List<Exemplar> disponiveis = exemplarRepo.findByLivroIdAndStatus(livroId, "DISPONIVEL");

        if (!disponiveis.isEmpty()) {
            Exemplar ex = disponiveis.get(0);
            ex.setStatus("EMPRESTADO");
            exemplarRepo.save(ex);

            Emprestimo emp = new Emprestimo();
            emp.setExemplar(ex);
            emp.setUsuario(usuario);
            emp.setDataEmprestimo(LocalDateTime.now());
            emp.setDataPrevistaDevolucao(LocalDate.now().plusDays(PRAZO_DIAS));
            emp.setStatus("ABERTO");
            return emprestimoRepo.save(emp);
        }

        // Nenhum exemplar disponível → sinalizar erro para criar reserva
        throw new RuntimeException("Nenhum exemplar disponível. Faça uma reserva.");
    }

    @Transactional
    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emp = emprestimoRepo.findById(emprestimoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado"));

        if ("DEVOLVIDO".equals(emp.getStatus())) {
            throw new RuntimeException("Empréstimo já devolvido");
        }

        emp.setDataDevolucao(LocalDateTime.now());
        emp.setStatus("DEVOLVIDO");
        emprestimoRepo.save(emp);

        Exemplar ex = emp.getExemplar();
        ex.setStatus("DISPONIVEL");
        exemplarRepo.save(ex);

        // verificar fila de reservas
        List<Reserva> fila = reservaRepo.findByLivroIdAndStatusOrderByPosicao(ex.getLivro().getId(), "ATIVA");
        if (!fila.isEmpty()) {
            Reserva r = fila.get(0);
            r.setStatus("ATENDIDA");
            reservaRepo.save(r);
            // empresta ao reservante automaticamente
            emprestar(ex.getLivro().getId(), r.getUsuario().getId());
        }

        return emp;
    }
}
