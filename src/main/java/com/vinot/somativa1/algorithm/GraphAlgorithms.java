package com.vinot.somativa1.algorithm;

import com.vinot.somativa1.model.Book;

import java.util.*;

/** Algoritmos de grafo para recomendações. */
public final class GraphAlgorithms {

    private GraphAlgorithms() {}

    /** BFS/Dijkstra não‑ponderado: distância mínima em arestas. */
    public static Map<Book,Integer> dijkstraSimple(HashMap<Book, Set<Book>> graph,
                                                   Book source) {
        Map<Book,Integer> dist = new HashMap<>();
        Queue<Book> queue      = new LinkedList<>();

        dist.put(source, 0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Book cur = queue.poll();
            int  d   = dist.get(cur);

            for (Book n : graph.getOrDefault(cur, Collections.emptySet())) {
                if (!dist.containsKey(n)) {
                    dist.put(n, d + 1);
                    queue.add(n);
                }
            }
        }
        return dist;
    }

    /** Devolve livros mais próximos a {@code source}, ordenados por distância. */
    public static List<Book> getClosestBooks(HashMap<Book, Set<Book>> graph,
                                             Book source,
                                             int limit) {

        Map<Book,Integer> dist = dijkstraSimple(graph, source);
        dist.remove(source);                       // não recomenda ele mesmo

        List<Book> ordered = new ArrayList<>(dist.keySet());
        ordered.sort(
                Comparator.<Book>comparingInt(dist::get)            // tipo explícito evita erro
                        .thenComparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER)
        );

        return (limit > 0 && ordered.size() > limit)
                ? ordered.subList(0, limit)
                : ordered;
    }
}
