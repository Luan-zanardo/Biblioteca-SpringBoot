1) Definição do problema

Pequenas bibliotecas (escolares, comunitárias ou de prédios) precisam de um sistema simples para gerir acervo, usuários, empréstimos e reservas. Atualmente usam controle manual em papel ou planilhas, o que gera conflitos de horários, perda de histórico de empréstimos, e dificuldade em saber onde estão os exemplares.

O sistema resolverá: organização do acervo (livros, autores, categorias, exemplares), cadastro de usuários, registro de empréstimos e devoluções, e sistema de reservas quando todos os exemplares estiverem emprestados.

2) Solução proposta — funcionalidades e fluxo geral

Funcionalidades principais

-CRUD de Usuários, Autores, Categorias, Livros e Exemplares.
-Visualizar disponibilidade de exemplares de um livro.
-Registrar Empréstimo (emprestar exemplar a usuário).
-Registrar Devolução (fechar empréstimo).
-Registrar Reserva: quando não há exemplar disponível, usuário pode reservar; reservas em fila FIFO.
-Relatórios simples: histórico de empréstimos por usuário; livros mais emprestados.
-Validações: limite de empréstimos por usuário, prazo máximo de empréstimo, não permitir múltiplos empréstimos do mesmo exemplar.

Fluxo de Empréstimo (resumido)

1. Usuário solicita emprestar um livro.
2. Sistema verifica se há exemplar disponível (exemplar.status = DISPONIVEL).
3. Se disponível: cria registro Emprestimo (dataEmprestimo, dataPrevistaDevolucao), muda exemplar.status = EMPRESTADO.
4. Se não disponível: usuário pode criar Reserva; reservas ordenadas por data.

Fluxo de Reserva

1. Usuário cria reserva para um livro.
2. Reservas em fila (posicao atribuída automaticamente).
3. Ao devolver exemplar, sistema aloca exemplar ao primeiro usuário da fila (notificação simulada — para trabalho, basta status e registro).
4. Reserva é marcada como ATENDIDA quando exemplar é emprestado a quem reservou.
