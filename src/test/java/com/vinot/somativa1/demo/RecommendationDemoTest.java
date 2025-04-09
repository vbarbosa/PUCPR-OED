package com.vinot.somativa1.demo;

import com.vinot.somativa1.controller.Library;
import com.vinot.somativa1.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationDemoTest {

    private Library library;
    private Book livro1984;

    @BeforeEach
    void setup() {
        library = new Library();
        LinkedList<Book> livros = new LinkedList<>();

        livro1984 = new Book("1984", "George Orwell", 1949);
        Book b1 = new Book("Admirável Mundo Novo", "Aldous Huxley", 1932);
        Book b2 = new Book("Fahrenheit 451", "Ray Bradbury", 1953);

        livro1984.updateBookHash();
        b1.updateBookHash();
        b2.updateBookHash();

        library.addBook(livro1984);
        library.addBook(b1);
        library.addBook(b2);

        library.addRecommendation(livro1984, b1);
        library.addRecommendation(livro1984, b2);
    }

    @Test
    void testRecommendationGraphFor1984() {
        Set<Book> recomendacoes = library.getRecommendations(livro1984);
        assertTrue(recomendacoes.stream().anyMatch(b -> b.getTitle().contains("Admirável")),
                "Recomendação 'Admirável Mundo Novo' não encontrada.");
        assertTrue(recomendacoes.stream().anyMatch(b -> b.getTitle().contains("Fahrenheit")),
                "Recomendação 'Fahrenheit 451' não encontrada.");
    }
}
