package com.vinot.somativa1.demo;

import com.vinot.somativa1.controller.Library;
import com.vinot.somativa1.model.Book;

import java.util.List;
import java.util.Set;

/**
 * Demonstração da Atividade 2 e 3:
 * - Seleção de livros e construção do grafo de recomendações
 * - Geração de sugestões com base em leitura prévia
 */
public class RecommendationDemo {

    public static void main(String[] args) {
        Library library = new Library();

        // Etapa 2: Adiciona 10 livros diferentes
        Book b1 = new Book("1984", "George Orwell", 1949);
        Book b2 = new Book("Admirável Mundo Novo", "Aldous Huxley", 1932);
        Book b3 = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
        Book b4 = new Book("O Conto da Aia", "Margaret Atwood", 1985);
        Book b5 = new Book("Laranja Mecânica", "Anthony Burgess", 1962);
        Book b6 = new Book("A Revolução dos Bichos", "George Orwell", 1945);
        Book b7 = new Book("Neuromancer", "William Gibson", 1984);
        Book b8 = new Book("Duna", "Frank Herbert", 1965);
        Book b9 = new Book("Fundação", "Isaac Asimov", 1951);
        Book b10 = new Book("2001: Uma Odisseia no Espaço", "Arthur C. Clarke", 1968);

        List<Book> allBooks = List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
        allBooks.forEach(b -> {
            b.updateBookHash();
            library.addBook(b);
        });

        // Etapa 2: Cada livro com pelo menos duas recomendações
        library.addRecommendation(b1, b2);
        library.addRecommendation(b1, b3);
        library.addRecommendation(b2, b1);
        library.addRecommendation(b2, b4);
        library.addRecommendation(b3, b5);
        library.addRecommendation(b3, b6);
        library.addRecommendation(b4, b1);
        library.addRecommendation(b4, b7);
        library.addRecommendation(b5, b3);
        library.addRecommendation(b5, b8);
        library.addRecommendation(b6, b2);
        library.addRecommendation(b6, b9);
        library.addRecommendation(b7, b10);
        library.addRecommendation(b7, b6);
        library.addRecommendation(b8, b9);
        library.addRecommendation(b8, b10);
        library.addRecommendation(b9, b4);
        library.addRecommendation(b9, b5);
        library.addRecommendation(b10, b1);
        library.addRecommendation(b10, b2);

        // Etapa 3: Simula leitura anterior do livro "1984"
        Book leituraAnterior = b1;
        Set<Book> recomendacoes = library.getRecommendations(leituraAnterior);

        System.out.println("\nUsuário leu: " + leituraAnterior.getTitle());
        System.out.println("Recomendações com base nessa leitura:");
        recomendacoes.forEach(b ->
                System.out.println(" - " + b.getTitle() + " (" + b.getAuthor() + ")")
        );
    }
}
