package com.vinot.somativa1.controller;

import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.InventoryItem;
import com.vinot.somativa1.model.User;

import java.util.*;
/**
 * Classe de controle da biblioteca.
 * Gerencia livros, usuários, inventário e as relações entre livros para recomendação.
 */
public class Library {
    private final List<Book> books = new LinkedList<>();
    private final Map<String, User> users = new HashMap<>();
    private final Map<Integer, InventoryItem> inventory = new HashMap<>();

    /**
     * Adiciona um novo livro à biblioteca.
     * @param book livro a ser adicionado
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
     * Remove um livro com base no hash identificador.
     * @param book identificador do livro
     */
    public void removeBook(Book book) {
        books.remove(book);
        int hash = book.hashCode();
        if (inventory.containsKey(hash)) {
            inventory.get(hash).removePhysicalCopy();
        }
    }

    /**
     * Exibe todos os livros cadastrados.
     */
    public void displayBooks() {
        books.forEach(System.out::println);
    }

    public int getTotalBooks() {
        return books.size();
    }

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
     * Adiciona um novo usuário à biblioteca.
     * @param name o usuário a ser adicionado
     */
    public void addUser(String name, String email, String userId) {
        if (name != null && email != null && userId != null) {
            users.put(userId, new User(name, email, userId));
        }
    }

    /**
     * Remove um usuário da biblioteca com base no ID.
     * @param userId identificador do usuário
     */
    public void removeUser(String userId) {
        users.remove(userId);
    }

    /**
     * Retorna um usuário com base no ID, se existir.
     * @param userId identificador do usuário
     * @return usuário encontrado ou vazio
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

    public void displayInventory() {
        System.out.println("\n--- Inventário ---");
        inventory.values().forEach(System.out::println);
    }
}
