package com.vinot.somativa1.controller;

import com.vinot.somativa1.model.User;

import java.util.*;

/**
 * Gerencia a lista de espera para livros emprestados.
 * Utiliza uma fila (FIFO) para garantir ordem de chegada.
 */
public class BookQueueManager {

    private final Map<Integer, Queue<User>> bookWaitlist = new HashMap<>();

    /**
     * Adiciona um usuário à fila de espera de um livro.
     *
     * @param bookHash hashCode único do livro
     * @param user usuário a ser adicionado na fila
     */
    public void addToWaitlist(int bookHash, User user) {
        bookWaitlist.computeIfAbsent(bookHash, k -> new LinkedList<>()).add(user);
    }

    /**
     * Retorna o próximo usuário da fila de espera de um livro, removendo-o da fila.
     *
     * @param bookHash hashCode do livro
     * @return próximo usuário da fila ou null se a fila estiver vazia
     */
    public User getNextInWaitlist(int bookHash) {
        Queue<User> queue = bookWaitlist.get(bookHash);
        return (queue != null && !queue.isEmpty()) ? queue.poll() : null;
    }

    /**
     * Retorna a fila de espera atual de um livro sem removê-la.
     *
     * @param bookHash hashCode do livro
     * @return lista com os usuários na fila de espera
     */
    public List<User> viewWaitlist(int bookHash) {
        return new ArrayList<>(bookWaitlist.getOrDefault(bookHash, new LinkedList<>()));
    }
}
