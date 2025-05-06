package com.vinot.somativa1.algorithm;

import com.vinot.somativa1.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste unitário “falante” que mostra o funcionamento do Dijkstra simples.
 */
public class GraphAlgorithmsVerboseTest {

    /** Implementação local do algoritmo com prints. */
    private Map<Book,Integer> dijkstraVerbose(HashMap<Book, Set<Book>> graph, Book source) {
        Map<Book,Integer> dist  = new HashMap<>();
        Queue<Book> queue       = new LinkedList<>();

        dist.put(source, 0);
        queue.add(source);

        System.out.println("== Início da BFS (Dijkstra não‑ponderado) ==");
        while (!queue.isEmpty()) {
            Book cur = queue.poll();
            int  d   = dist.get(cur);
            System.out.printf("Visitando %-15s | distância = %d%n", cur.getTitle(), d);

            for (Book n : graph.getOrDefault(cur, Collections.emptySet())) {
                if (!dist.containsKey(n)) {
                    dist.put(n, d + 1);
                    queue.add(n);
                    System.out.printf("  ↳ Descoberto %-12s | distância = %d%n", n.getTitle(), d + 1);
                }
            }
        }
        System.out.println("== Fim da BFS ==\n");
        return dist;
    }

    @Test
    @DisplayName("Imprime o mapa completo de distâncias")
    void verboseRun() {
        Book a = new Book("A");
        Book b = new Book("B");
        Book c = new Book("C");
        Book d = new Book("D");

        HashMap<Book, Set<Book>> g = new HashMap<>();
        g.put(a, Set.of(b, c));  // A → B, C
        g.put(b, Set.of(d));     // B → D
        g.put(c, Set.of(d));     // C → D
        g.put(d, Set.of());      // D → ∅

        Map<Book,Integer> dist = dijkstraVerbose(g, a);

        System.out.println("Mapa final de distâncias:");
        dist.forEach((book, dVal) ->
                System.out.printf("  %s -> %d%n", book.getTitle(), dVal));

        // Asserções para garantir que ainda é um teste “valendo”
        assertEquals(4, dist.size());
        assertEquals(0, dist.get(a));
        assertEquals(1, dist.get(b));
        assertEquals(1, dist.get(c));
        assertEquals(2, dist.get(d));
    }
}
