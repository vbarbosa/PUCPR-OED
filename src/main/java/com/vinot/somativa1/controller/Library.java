package com.vinot.somativa1.controller;

import com.vinot.somativa1.algorithm.GraphAlgorithms;
import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.InventoryItem;
import com.vinot.somativa1.model.User;

import java.text.Normalizer;
import java.util.*;

/** Classe de controle da biblioteca virtual. */
public class Library {

    /* ---------- Campos ---------- */
    private final List<Book> books = new LinkedList<>();
    private final Map<String, User> users = new HashMap<>();
    private final Map<Integer, InventoryItem> inventory = new HashMap<>();
    private final Map<Book, Set<Book>> recommendationGraph = new HashMap<>();

    private final BookQueueManager   bookQueueManager   = new BookQueueManager();
    private final UserHistoryManager userHistoryManager = new UserHistoryManager();

    /* ---------- Util ---------- */
    private static String normalize(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    /* ---------- CRUD Livros ---------- */
    public void addBook(Book book) {
        if (book == null) return;
        books.add(book);
        int h = book.hashCode();
        inventory.putIfAbsent(h, new InventoryItem(h));
        inventory.get(h).setDigitalCopies(book.getDigitalCopyHashes().size());
        for (int i = 0; i < book.getPhysicalCopyHashes().size(); i++)
            inventory.get(h).addPhysicalCopy();
    }
    public void removeBook(Book b){ books.remove(b); inventory.remove(b.hashCode()); }
    public void displayBooks(){ books.forEach(System.out::println); }
    public int  getTotalBooks(){ return books.size(); }

    public List<Book> searchBookByTitle(String q){
        if(q==null||q.isBlank()) return Collections.emptyList();
        String n = normalize(q);
        return books.stream()
                .filter(b->normalize(b.getTitle()).contains(n))
                .toList();
    }

    /* ---------- CRUD Usuários ---------- */
    public void addUser(String n,String e,String id){ if(n!=null&&e!=null&&id!=null) users.put(id,new User(n,e,id)); }
    public void removeUser(String id){ users.remove(id); }
    public Optional<User> getUser(String id){ return Optional.ofNullable(users.get(id)); }
    public void displayUsers(){ users.values().forEach(System.out::println); }

    /* ---------- Inventário ---------- */
    public void displayInventory(){ inventory.values().forEach(System.out::println); }

    /* ---------- Grafo ---------- */
    public void addRecommendation(Book base,Book rec){
        recommendationGraph.putIfAbsent(base,new HashSet<>());
        recommendationGraph.putIfAbsent(rec,new HashSet<>());
        recommendationGraph.get(base).add(rec);
    }
    public Set<Book> getRecommendations(Book b){ return recommendationGraph.getOrDefault(b,Collections.emptySet()); }
    public void displayRecommendationGraph(){
        System.out.println("\n--- Grafo de Recomendações ---");
        recommendationGraph.forEach((k,v)-> System.out.println(k+" -> "+v));
    }

    /* ---------- Gerenciadores ---------- */
    public BookQueueManager getBookQueueManager(){ return bookQueueManager; }
    public UserHistoryManager getUserHistoryManager(){ return userHistoryManager; }

    /* ---------- Dijkstra ---------- */
    public Map<Book,Integer> getDistances(Book origem){
        Map<Book,Integer> d = GraphAlgorithms.dijkstraSimple(
                (HashMap<Book,Set<Book>>) recommendationGraph, origem);
        d.remove(origem);
        return d;
    }
    public List<Map.Entry<Book,Integer>> getDistancesOrdered(Book origem){
        Map<Book,Integer> d = getDistances(origem);
        return d.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Book,Integer> e)->e.getValue())
                                .thenComparing((Map.Entry<Book,Integer> e)->e.getKey().getTitle(),
                                        String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    /** ======= Exibe distâncias simples (case 21) ======= */
    public void displayDistances(Book origem){
        var ordered = getDistancesOrdered(origem);
        if(ordered.isEmpty()){
            System.out.println("Nenhum outro livro alcançável a partir de \""+origem.getTitle()+"\".");
            return;
        }
        System.out.println("Distâncias (arestas) a partir de \""+origem.getTitle()+"\":");
        ordered.forEach(e->
                System.out.printf("  %d → %s%n", e.getValue(), e.getKey().getTitle()));
    }

    /** ======= Passo‑a‑passo + caminho (case 22) ======= */
    public void displayDistancesVerbose(Book origem){

        HashMap<Book,Set<Book>> g = (HashMap<Book,Set<Book>>)recommendationGraph;
        Map<Book,Integer> dist = new HashMap<>();
        Map<Book,Book> pred   = new HashMap<>();
        Queue<Book> q        = new LinkedList<>();

        dist.put(origem,0); q.add(origem);

        System.out.println("== Início da BFS (Dijkstra não‑ponderado) ==");
        while(!q.isEmpty()){
            Book cur = q.poll(); int d = dist.get(cur);
            System.out.printf("Visitando %-30s | distância = %d%n",cur.getTitle(),d);
            for(Book n:g.getOrDefault(cur,Collections.emptySet())){
                if(!dist.containsKey(n)){
                    dist.put(n,d+1); pred.put(n,cur); q.add(n);
                    System.out.printf("  ↳ Descoberto %-27s | distância = %d%n",n.getTitle(),d+1);
                }
            }
        }
        System.out.println("== Fim da BFS ==\n");

        if(dist.size()==1){ System.out.println("Nenhum outro livro alcançável."); return; }

        var ordered = dist.entrySet().stream()
                .filter(e->!e.getKey().equals(origem))
                .sorted(
                        Comparator.comparingInt((Map.Entry<Book,Integer> e)->e.getValue())
                                .thenComparing((Map.Entry<Book,Integer> e)->e.getKey().getTitle(),
                                        String.CASE_INSENSITIVE_ORDER))
                .toList();

        System.out.println("Distância e caminho (origem = \""+origem.getTitle()+"\"):");
        for(var e:ordered){
            Book dest=e.getKey(); int dVal=e.getValue();
            List<Book> path=new ArrayList<>();
            for(Book at=dest; at!=null; at=pred.get(at)){ path.add(at); if(at.equals(origem)) break; }
            Collections.reverse(path);
            String pStr=path.stream().map(Book::getTitle).reduce((a,b)->a+" -> "+b).orElse(dest.getTitle());
            System.out.printf("  %d  | %s%n",dVal,pStr);
        }
    }

    /* ---------- Seed opcional ---------- */
    public void seedSampleGraph(){
        Map<String,String[]> edges = Map.of(
                "1984", new String[]{"A Revolução dos Bichos","Brave New World"},
                "Brave New World", new String[]{"Cem Anos de Solidão"}
        );
        edges.forEach((src,dsts)->{
            Book srcB = searchBookByTitle(src).stream().findFirst().orElse(null);
            if(srcB==null) return;
            for(String d:dsts){
                Book dstB = searchBookByTitle(d).stream().findFirst().orElse(null);
                if(dstB!=null) addRecommendation(srcB,dstB);
            }
        });
    }
}
