package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entity.*;
import com.biblioteca.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.biblioteca.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepo;
    private final UsuarioRepository usuarioRepo;
    private final LivroRepository livroRepo;

    @Transactional
    public Reserva criarReserva(Long livroId, Long usuarioId) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Livro l = livroRepo.findById(livroId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        List<Reserva> filaAtiva = reservaRepo.findByLivroIdAndStatus(livroId, "ATIVA");
        int pos = filaAtiva.size() + 1;

        Reserva r = new Reserva();
        r.setLivro(l);
        r.setUsuario(u);
        r.setDataReserva(LocalDateTime.now());
        r.setPosicao(pos);
        r.setStatus("ATIVA");
        return reservaRepo.save(r);
    }
}
