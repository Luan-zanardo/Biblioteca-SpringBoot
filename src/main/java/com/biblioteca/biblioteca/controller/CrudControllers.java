package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entity.*;
import com.biblioteca.biblioteca.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CrudControllers {

    private final UsuarioRepository usuarioRepo;
    private final AutorRepository autorRepo;
    private final CategoriaRepository categoriaRepo;
    private final LivroRepository livroRepo;
    private final ExemplarRepository exemplarRepo;

    // ============================
    // USUÁRIOS
    // ============================

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() { return usuarioRepo.findAll(); }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return usuarioRepo.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/usuarios")
    public Usuario novoUsuario(@RequestBody Usuario u) { return usuarioRepo.save(u); }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioRepo.findById(id).map(u -> {
            u.setNome(dados.getNome());
            u.setEmail(dados.getEmail());
            u.setTelefone(dados.getTelefone());
            u.setAtivo(dados.getAtivo());
            return ResponseEntity.ok(usuarioRepo.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (!usuarioRepo.existsById(id)) return ResponseEntity.notFound().build();
        usuarioRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // ============================
    // AUTORES
    // ============================

    @GetMapping("/autores")
    public List<Autor> listarAutores() { return autorRepo.findAll(); }

    @GetMapping("/autores/{id}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable Long id) {
        return autorRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/autores")
    public Autor novoAutor(@RequestBody Autor a) { return autorRepo.save(a); }

    @PutMapping("/autores/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody Autor dados) {
        return autorRepo.findById(id).map(a -> {
            a.setNome(dados.getNome());
            a.setBiografia(dados.getBiografia());
            return ResponseEntity.ok(autorRepo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        if (!autorRepo.existsById(id)) return ResponseEntity.notFound().build();
        autorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // ============================
    // CATEGORIAS
    // ============================

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() { return categoriaRepo.findAll(); }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id) {
        return categoriaRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categorias")
    public Categoria novaCategoria(@RequestBody Categoria c) { return categoriaRepo.save(c); }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria dados) {
        return categoriaRepo.findById(id).map(c -> {
            c.setNome(dados.getNome());
            return ResponseEntity.ok(categoriaRepo.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        if (!categoriaRepo.existsById(id)) return ResponseEntity.notFound().build();
        categoriaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // ============================
    // LIVROS
    // ============================

    @GetMapping("/livros")
    public List<Livro> listarLivros() { return livroRepo.findAll(); }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id) {
        return livroRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/livros")
    public Livro novoLivro(@RequestBody Livro l) { return livroRepo.save(l); }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro dados) {
        return livroRepo.findById(id).map(l -> {
            l.setTitulo(dados.getTitulo());
            l.setIsbn(dados.getIsbn());
            l.setAno(dados.getAno());
            l.setSinopse(dados.getSinopse());
            l.setAutor(dados.getAutor());
            l.setCategoria(dados.getCategoria());
            return ResponseEntity.ok(livroRepo.save(l));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (!livroRepo.existsById(id)) return ResponseEntity.notFound().build();
        livroRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // ============================
    // EXEMPLARES
    // ============================

    @GetMapping("/exemplares")
    public List<Exemplar> listarExemplares() { return exemplarRepo.findAll(); }

    @GetMapping("/exemplares/{id}")
    public ResponseEntity<Exemplar> buscarExemplar(@PathVariable Long id) {
        return exemplarRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/exemplares")
    public Exemplar novoExemplar(@RequestBody Exemplar e) { return exemplarRepo.save(e); }

    @PutMapping("/exemplares/{id}")
    public ResponseEntity<Exemplar> atualizarExemplar(@PathVariable Long id, @RequestBody Exemplar dados) {
        return exemplarRepo.findById(id).map(e -> {
            e.setCodigo(dados.getCodigo());
            e.setStatus(dados.getStatus());
            e.setLocalizacao(dados.getLocalizacao());
            e.setLivro(dados.getLivro());
            return ResponseEntity.ok(exemplarRepo.save(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/exemplares/{id}")
    public ResponseEntity<Void> deletarExemplar(@PathVariable Long id) {
        if (!exemplarRepo.existsById(id)) return ResponseEntity.notFound().build();
        exemplarRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
