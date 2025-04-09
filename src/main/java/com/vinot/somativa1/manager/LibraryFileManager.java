package com.vinot.somativa1.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vinot.somativa1.controller.Library;
import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Responsável por salvar e carregar dados de livros e usuários em arquivos JSON,
 * utilizando a biblioteca Jackson.
 */
public class LibraryFileManager {
    private static File BOOKS_FILE = new File("src/main/resources/books.json");
    private static File USERS_FILE = new File("src/main/resources/users.json");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveBooks(LinkedList<Book> books, Library library) {
        try {
            ArrayNode bookArray = objectMapper.createArrayNode();
            for (Book book : books) {
                JsonNode bookNode = objectMapper.valueToTree(book);
                Set<Book> recs = library.getRecommendations(book);
                ArrayNode recHashes = objectMapper.createArrayNode();
                for (Book rec : recs) {
                    recHashes.add(rec.getBookHash());
                }
                ((ObjectNode) bookNode).set("recommendations", recHashes);
                bookArray.add(bookNode);
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_FILE, bookArray);
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

    public static void restoreRecommendations(Library library, LinkedList<Book> books) {
        try {
            if (!BOOKS_FILE.exists()) return;
            JsonNode root = objectMapper.readTree(BOOKS_FILE);
            Map<Integer, Book> hashMap = new HashMap<>();
            for (Book b : books) hashMap.put(b.getBookHash(), b);

            for (JsonNode node : root) {
                int hash = node.get("bookHash").asInt();
                Book base = hashMap.get(hash);
                if (node.has("recommendations") && base != null) {
                    for (JsonNode rec : node.get("recommendations")) {
                        Book recBook = hashMap.get(rec.asInt());
                        if (recBook != null) {
                            library.addRecommendation(base, recBook);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao restaurar recomendações: " + e.getMessage());
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

