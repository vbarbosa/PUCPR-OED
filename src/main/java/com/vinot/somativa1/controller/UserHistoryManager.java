package com.vinot.somativa1.controller;

import com.vinot.somativa1.model.Book;

import java.util.*;

/**
 * Armazena o histórico de navegação de livros de cada usuário.
 * Utiliza uma pilha (LIFO) para registrar os livros mais recentemente visualizados.
 */
public class UserHistoryManager {

    private final Map<String, Stack<Book>> history = new HashMap<>();

    /**
     * Registra a visualização de um livro por um usuário.
     *
     * @param userId ID do usuário
     * @param book   livro visualizado
     */
    public void visitBook(String userId, Book book) {
        history.computeIfAbsent(userId, k -> new Stack<>()).push(book);
    }

    /**
     * Retorna o histórico completo de livros visualizados por um usuário.
     * O livro mais recente aparece por último.
     *
     * @param userId ID do usuário
     * @return lista com os livros visualizados
     */
    public List<Book> getUserHistory(String userId) {
        return new ArrayList<>(history.getOrDefault(userId, new Stack<>()));
    }

    /**
     * Remove o último livro acessado do histórico.
     *
     * @param userId ID do usuário
     * @return o último livro acessado ou null se o histórico estiver vazio
     */
    public Book popLastVisited(String userId) {
        Stack<Book> stack = history.get(userId);
        return (stack != null && !stack.isEmpty()) ? stack.pop() : null;
    }
}
