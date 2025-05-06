package com.vinot.somativa1.algorithm;

import com.vinot.somativa1.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários do algoritmo de Dijkstra não‑ponderado.
 */
public class GraphAlgorithmsTest {

    private HashMap<Book, Set<Book>> buildGraph(Book a, Book b, Book c, Book d) {
        HashMap<Book, Set<Book>> g = new HashMap<>();
        g.put(a, Set.of(b, c)); // A → B, C
        g.put(b, Set.of(d));    // B → D
        g.put(c, Set.of(d));    // C → D
        g.put(d, Set.of());     // D sem vizinhos
        return g;
    }

    @Test
    @DisplayName("dijkstraSimple devolve distâncias corretas")
    void testDijkstraSimple() {
        Book a = new Book("A");
        Book b = new Book("B");
        Book c = new Book("C");
        Book d = new Book("D");

        Map<Book, Integer> dist =
                GraphAlgorithms.dijkstraSimple(buildGraph(a, b, c, d), a);

        assertEquals(0, dist.get(a));
        assertEquals(1, dist.get(b));
        assertEquals(1, dist.get(c));
        assertEquals(2, dist.get(d));
        assertEquals(4, dist.size());          // todos alcançáveis
    }

    @Test
    @DisplayName("getClosestBooks respeita ordenação e limite")
    void testGetClosestBooks() {
        Book a = new Book("A");
        Book b = new Book("B");
        Book c = new Book("C");
        Book d = new Book("D");

        List<Book> ordered =
                GraphAlgorithms.getClosestBooks(buildGraph(a, b, c, d), a, 2);

        assertEquals(2, ordered.size());               // limite aplicado
        assertTrue (ordered.containsAll(List.of(b, c)));
        assertFalse(ordered.contains(d));              // dist 2 ficou fora
    }
}
