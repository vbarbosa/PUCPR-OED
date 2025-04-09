package com.vinot.somativa1.controller;

import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.InventoryItem;
import com.vinot.somativa1.model.User;

import java.util.*;

/**
 * Classe principal de controle da biblioteca.
 * Responsável por gerenciar livros, usuários, inventário,
 * recomendações, filas de espera e histórico de navegação.
 */
public class Library {
    private final List<Book> books;
    private final Map<String, User> users;
    private final Map<Integer, InventoryItem> inventory;

    // Grafo de recomendações entre livros
    private final Map<Book, Set<Book>> recommendationGraph;

    // Gerenciadores de fila de espera e histórico de navegação
    private final BookQueueManager bookQueueManager;
    private final UserHistoryManager userHistoryManager;

    public Library() {
        this.books = new LinkedList<>();
        this.users = new HashMap<>();
        this.inventory = new HashMap<>();
        this.recommendationGraph = new HashMap<>();
        this.bookQueueManager = new BookQueueManager();
        this.userHistoryManager = new UserHistoryManager();
    }

    /**
     * Adiciona um novo livro à biblioteca.
     *
     * @param book o livro a ser adicionado
     */
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            int hash = book.hashCode();
            inventory.putIfAbsent(hash, new InventoryItem(hash));
            for (int i = 0; i < book.getPhysicalCopyHashes().size(); i++) {
                inventory.get(hash).addPhysicalCopy();
            }
            inventory.get(hash).setDigitalCopies(book.getDigitalCopyHashes().size());
        }
    }

    /**
     * Remove um livro da biblioteca e ajusta o inventário.
     *
     * @param book o livro a ser removido
     */
    public void removeBook(Book book) {
        books.remove(book);
        int hash = book.hashCode();
        inventory.remove(hash);
    }

    /**
     * Exibe todos os livros cadastrados.
     */
    public void displayBooks() {
        books.forEach(System.out::println);
    }

    /**
     * Busca livros cujo título contenha a string informada (case-insensitive).
     *
     * @param title termo de busca
     * @return lista de livros encontrados
     */
    public List<Book> searchBookByTitle(String title) {
        if (title == null || title.isBlank()) return Collections.emptyList();
        String lower = title.toLowerCase();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lower)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Retorna o total de livros cadastrados.
     *
     * @return quantidade de livros
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Adiciona um novo usuário.
     *
     * @param name nome
     * @param email e-mail
     * @param userId identificador único
     */
    public void addUser(String name, String email, String userId) {
        if (name != null && email != null && userId != null) {
            users.put(userId, new User(name, email, userId));
        }
    }

    /**
     * Remove um usuário da biblioteca.
     *
     * @param userId ID do usuário
     */
    public void removeUser(String userId) {
        users.remove(userId);
    }

    /**
     * Retorna um usuário, se existir.
     *
     * @param userId ID do usuário
     * @return usuário, ou Optional vazio
     */
    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    /**
     * Exibe todos os usuários cadastrados.
     */
    public void displayUsers() {
        users.values().forEach(System.out::println);
    }

    /**
     * Exibe o inventário atual de livros.
     */
    public void displayInventory() {
        System.out.println("\n--- Inventário ---");
        inventory.values().forEach(System.out::println);
    }

    /**
     * Acesso ao gerenciador de fila de espera.
     *
     * @return instância do BookQueueManager
     */
    public BookQueueManager getBookQueueManager() {
        return bookQueueManager;
    }

    /**
     * Acesso ao gerenciador de histórico do usuário.
     *
     * @return instância do UserHistoryManager
     */
    public UserHistoryManager getUserHistoryManager() {
        return userHistoryManager;
    }
}
