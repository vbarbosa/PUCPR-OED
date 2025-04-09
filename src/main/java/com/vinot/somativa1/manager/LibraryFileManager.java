package com.vinot.somativa1.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Responsável por salvar e carregar dados de livros e usuários em arquivos JSON,
 * utilizando a biblioteca Jackson.
 */
public class LibraryFileManager {
    private static File BOOKS_FILE = new File("src/main/resources/books.json");
    private static File USERS_FILE = new File("src/main/resources/users.json");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveBooks(LinkedList<Book> books) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_FILE, books);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    public static void overridePaths(File bookFile, File userFile) {
        BOOKS_FILE = bookFile;
        USERS_FILE = userFile;
    }


    public static LinkedList<Book> loadBooks() {
        try {
            if (!BOOKS_FILE.exists()) return new LinkedList<>();
            return objectMapper.readValue(BOOKS_FILE, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.err.println("Erro ao carregar os livros: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    public static void saveUsers(Map<String, User> users) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, users);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    public static Map<String, User> loadUsers() {
        try {
            if (!USERS_FILE.exists()) return new HashMap<>();
            return objectMapper.readValue(USERS_FILE, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
