package com.vinot.somativa1.application;

import com.vinot.somativa1.controller.Library;
import com.vinot.somativa1.manager.LibraryFileManager;
import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.User;

import java.io.File;
import java.util.*;

/**
 * Classe principal que inicia a aplicação da biblioteca virtual.
 * Responsável por carregar os dados salvos e iniciar o menu de interação
 * com o usuário. Inclui recursos para gerenciamento de livros, usuários,
 * fila de espera, histórico de navegação e recomendações.
 */
public class Main {

    static {
        // Garante que o diretório de recursos exista antes de iniciar a aplicação
        File resourceDir = new File("src/main/resources");
        if (!resourceDir.exists()) {
            resourceDir.mkdirs();
        }
    }

    /**
     * Limpa o console de acordo com o SO, se possível.
     * Em ambientes que não suportam a limpeza "real", imprime várias linhas em branco.
     */
    private static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback em ambientes que não suportam a limpeza real
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Método principal que executa o loop do menu de interação com o usuário.
     * Carrega dados de livros e usuários de arquivos e oferece diversas funções:
     * <ul>
     *   <li>Gerenciar livros (adicionar, remover, atualizar, listar, buscar)</li>
     *   <li>Gerenciar usuários (adicionar, remover, atualizar, listar, buscar)</li>
     *   <li>Gerenciar fila de espera de livros</li>
     *   <li>Exibir histórico de navegação de usuários</li>
     *   <li>Gerenciar recomendações (grafo de livros)</li>
     * </ul>
     *
     * @param args parâmetros de linha de comando (não usados aqui).
     */
    public static void main(String[] args) {

        // Instancia a biblioteca (que internamente possui BookQueueManager, UserHistoryManager e o grafo de recomendações)
        Library library = new Library();

        // Carrega livros e usuários já salvos em arquivo
        LinkedList<Book> livros = LibraryFileManager.loadBooks();
        Map<String, User> usuarios = LibraryFileManager.loadUsers();

        // Preenche o objeto library com os dados carregados
        livros.forEach(book -> {
            book.updateBookHash();
            library.addBook(book);
        });
        usuarios.values().forEach(u -> library.addUser(u.getName(), u.getEmail(), u.getUserId()));

        // Caso não haja livros, cria um conjunto inicial
        if (library.getTotalBooks() == 0) {
            Book b1 = new Book("1984", "George Orwell", 1949);
            Book b2 = new Book("O Senhor dos Anéis", "Tolkien");
            Book b3 = new Book("Dom Casmurro", "Machado de Assis", 1899);
            Book b4 = new Book("Cem Anos de Solidão", "Gabriel García Márquez");
            Book b5 = new Book("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
            Book b6 = new Book("A Revolução dos Bichos", "George Orwell", 1945);
            Book b7 = new Book("Crime e Castigo", "Fiódor Dostoiévski");
            Book b8 = new Book("O Hobbit", "J.R.R. Tolkien", 1937);
            Book b9 = new Book("O Apanhador no Campo de Centeio", "J.D. Salinger");
            Book b10 = new Book("Moby Dick", "Herman Melville", 1851);

            Book[] todos = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10};

            for (Book b : todos) {
                b.updateBookHash();
                library.addBook(b);
                livros.add(b);
            }

            // Adicionando algumas cópias de exemplo
            b1.addPhysicalCopy();
            b1.addDigitalCopy();
            b2.addPhysicalCopy();
            b3.addDigitalCopy();
            b5.addPhysicalCopy();
            b5.addPhysicalCopy();
            b7.addPhysicalCopy();
            b7.addDigitalCopy();
            b10.addPhysicalCopy();

            // Salvando após criar o acervo inicial
            LibraryFileManager.saveBooks(livros, library);
        }

        // Inicializa os usuários de teste, se necessário
        if (library.getUser("teste1").isEmpty() && library.getUser("teste2").isEmpty()) {
            User u1 = new User("Usuário Teste 1", "teste1@email.com", "teste1");
            User u2 = new User("Usuário Teste 2", "teste2@email.com", "teste2");
            library.addUser(u1.getName(), u1.getEmail(), u1.getUserId());
            library.addUser(u2.getName(), u2.getEmail(), u2.getUserId());
            usuarios.put(u1.getUserId(), u1);
            usuarios.put(u2.getUserId(), u2);
            LibraryFileManager.saveUsers(usuarios);
        }

        // Scanner para interações no console
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            clearConsole();
            System.out.println("\n--- Biblioteca Virtual ---");
            System.out.println("1. Adicionar livro ao acervo");
            System.out.println("2. Listar livros");
            System.out.println("3. Buscar livro por título");
            System.out.println("4. Adicionar cópias de um livro");
            System.out.println("5. Remover cópias de um livro");
            System.out.println("6. Ver inventário");
            System.out.println("7. Adicionar usuário");
            System.out.println("8. Listar usuários");
            System.out.println("9. Buscar usuário por ID");
            System.out.println("10. Atualizar dados de livro");
            System.out.println("11. Atualizar dados de usuário");
            System.out.println("12. Remover livro");
            System.out.println("13. Remover usuário");
            System.out.println("14. Ver hashCode de um livro");
            System.out.println("15. Salvar Tudo");

            // Funcionalidades já existentes de fila e histórico
            System.out.println("16. Adicionar usuário à fila de espera de um livro");
            System.out.println("17. Visualizar fila de espera de um livro");
            System.out.println("18. Exibir histórico de livros visualizados por um usuário");

            // NOVAS FUNCIONALIDADES DO GRAFO:
            System.out.println("19. Exibir recomendações para um livro");
            System.out.println("20. Adicionar recomendação entre dois livros");
            System.out.println("21. Mapa de distâncias (Dijkstra) a partir de um livro");
            System.out.println("22. Traçar Dijkstra passo a passo");
            System.out.println("23. Mostrar grafo completo");



            System.out.println("0. Fechar Biblioteca");
            System.out.print("Opção: ");

            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
                option = -1;
            } else {
                try {
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    option = -1;
                }
            }

            clearConsole();

            switch (option) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    System.out.print("Autor: ");
                    String author = scanner.nextLine();
                    System.out.print("Ano: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    Book book = new Book(title, author, year);
                    book.updateBookHash();
                    library.addBook(book);
                    livros.add(book);
                    LibraryFileManager.saveBooks(livros, library);
                    System.out.println("Livro adicionado.");
                }
                case 2 -> library.displayBooks();
                case 3 -> {
                    System.out.print("Título (ou parte): ");
                    String search = scanner.nextLine();
                    library.searchBookByTitle(search).forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    for (Book book : library.searchBookByTitle(title)) {
                        System.out.print("Cópias físicas: ");
                        int f = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digitais: ");
                        int d = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < f; i++) book.addPhysicalCopy();
                        for (int i = 0; i < d; i++) book.addDigitalCopy();
                    }
                    LibraryFileManager.saveBooks(livros, library);
                }
                case 5 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    for (Book book : library.searchBookByTitle(title)) {
                        if (!book.getPhysicalCopyHashes().isEmpty()) {
                            System.out.print("Remover física? (s/n): ");
                            if (scanner.nextLine().equalsIgnoreCase("s")) {
                                book.getPhysicalCopyHashes().remove(0);
                            }
                        }
                        if (!book.getDigitalCopyHashes().isEmpty()) {
                            System.out.print("Remover digital? (s/n): ");
                            if (scanner.nextLine().equalsIgnoreCase("s")) {
                                book.getDigitalCopyHashes().remove(0);
                            }
                        }
                    }
                    LibraryFileManager.saveBooks(livros, library);
                }
                case 6 -> library.displayInventory();
                case 7 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    User u = new User(name, email, id);
                    library.addUser(name, email, id);
                    usuarios.put(id, u);
                    LibraryFileManager.saveUsers(usuarios);
                    System.out.println("Usuário adicionado com sucesso.");
                }
                case 8 -> library.displayUsers();
                case 9 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    library.getUser(id).ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Usuário não encontrado.")
                    );
                }
                case 10 -> {
                    System.out.print("Título do livro: ");
                    String title = scanner.nextLine();
                    for (Book book : library.searchBookByTitle(title)) {
                        System.out.print("Novo título (ou Enter): ");
                        String t = scanner.nextLine();
                        if (!t.isBlank()) book.setTitle(t);
                        System.out.print("Novo autor (ou Enter): ");
                        String a = scanner.nextLine();
                        if (!a.isBlank()) book.setAuthor(a);
                        System.out.print("Novo ano (ou Enter): ");
                        String y = scanner.nextLine();
                        if (!y.isBlank()) book.setYear(Integer.parseInt(y));
                        book.updateBookHash();
                    }
                    LibraryFileManager.saveBooks(livros, library);
                }
                case 11 -> {
                    System.out.print("ID do usuário: ");
                    String id = scanner.nextLine();
                    library.getUser(id).ifPresent(user -> {
                        System.out.print("Novo nome (ou Enter): ");
                        String n = scanner.nextLine();
                        if (!n.isBlank()) user.setName(n);
                        System.out.print("Novo email (ou Enter): ");
                        String e = scanner.nextLine();
                        if (!e.isBlank()) user.setEmail(e);
                    });
                    LibraryFileManager.saveUsers(usuarios);
                }
                case 12 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    List<Book> toRemove = library.searchBookByTitle(title);
                    toRemove.forEach(library::removeBook);
                    livros.removeAll(toRemove);
                    LibraryFileManager.saveBooks(livros, library);
                }
                case 13 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    library.removeUser(id);
                    usuarios.remove(id);
                    LibraryFileManager.saveUsers(usuarios);
                }
                case 14 -> {
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    library.searchBookByTitle(title)
                            .forEach(b -> System.out.println("Hash: " + b.getBookHash()));
                }
                case 15 -> {
                    LibraryFileManager.saveBooks(livros, library);
                    LibraryFileManager.saveUsers(usuarios);
                    System.out.println("Salvando Tudo...");
                }
                // ======================================
                // Novas funcionalidades de Fila e Histórico (já implementadas)
                case 16 -> {
                    System.out.print("ID do usuário: ");
                    String userId = scanner.nextLine();
                    var maybeUser = library.getUser(userId);
                    if (maybeUser.isEmpty()) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }
                    User user = maybeUser.get();

                    System.out.print("Título do livro: ");
                    String title = scanner.nextLine();
                    List<Book> encontrados = library.searchBookByTitle(title);
                    if (encontrados.isEmpty()) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        // Usamos o primeiro livro encontrado
                        Book book = encontrados.get(0);
                        int bookHash = book.getBookHash();
                        library.getBookQueueManager().addToWaitlist(bookHash, user);
                        System.out.println("Usuário " + userId + " adicionado à fila de espera do livro '" + book.getTitle() + "'.");
                    }
                }
                case 17 -> {
                    System.out.print("Título do livro: ");
                    String title = scanner.nextLine();
                    List<Book> encontrados = library.searchBookByTitle(title);
                    if (encontrados.isEmpty()) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        Book book = encontrados.get(0);
                        int bookHash = book.getBookHash();
                        var waitlist = library.getBookQueueManager().viewWaitlist(bookHash);
                        if (waitlist.isEmpty()) {
                            System.out.println("Nenhum usuário na fila de espera para este livro.");
                        } else {
                            System.out.println("Fila de espera para '" + book.getTitle() + "':");
                            waitlist.forEach(u -> System.out.println(" - " + u.getName() + " (ID: " + u.getUserId() + ")"));
                        }
                    }
                }
                case 18 -> {
                    System.out.print("ID do usuário: ");
                    String userId = scanner.nextLine();
                    var maybeUser = library.getUser(userId);
                    if (maybeUser.isEmpty()) {
                        System.out.println("Usuário não encontrado.");
                    } else {
                        var visitedBooks = library.getUserHistoryManager().getUserHistory(userId);
                        if (visitedBooks.isEmpty()) {
                            System.out.println("Não há histórico para este usuário.");
                        } else {
                            System.out.println("Histórico de navegação de " + maybeUser.get().getName() + ":");
                            visitedBooks.forEach(b ->
                                    System.out.println(" - " + b.getTitle() + " (" + b.getAuthor() + ")"));
                        }
                    }
                }
                // ======================================
                // NOVAS FUNCIONALIDADES para o Grafo de Recomendações
                case 19 -> {
                    System.out.print("Título do livro para ver recomendações: ");
                    String title = scanner.nextLine();
                    List<Book> encontrados = library.searchBookByTitle(title);
                    if (encontrados.isEmpty()) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        Book livro = encontrados.get(0);
                        Set<Book> recs = library.getRecommendations(livro);
                        if (recs.isEmpty()) {
                            System.out.println("Sem recomendações disponíveis.");
                        } else {
                            System.out.println("Recomendações:");
                            recs.forEach(r -> System.out.println(" - " + r.getTitle()));
                        }
                    }
                }
                case 20 -> {
                    System.out.print("Livro base: ");
                    String base = scanner.nextLine();
                    System.out.print("Livro recomendado: ");
                    String rec = scanner.nextLine();
                    List<Book> baseList = library.searchBookByTitle(base);
                    List<Book> recList = library.searchBookByTitle(rec);
                    if (baseList.isEmpty() || recList.isEmpty()) {
                        System.out.println("Livro(s) não encontrado(s).");
                    } else {
                        library.addRecommendation(baseList.get(0), recList.get(0));
                        LibraryFileManager.saveBooks(livros, library);
                        System.out.println("Recomendação adicionada.");
                    }
                }
                case 21 -> {
                    System.out.print("Título do livro de origem: ");
                    String title = scanner.nextLine();
                    List<Book> encontrados = library.searchBookByTitle(title);
                    if (encontrados.isEmpty()) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        Book origem = encontrados.get(0);
                        library.displayDistances(origem);
                    }
                }
                case 22 -> {
                    System.out.print("Livro de origem: ");
                    String title = scanner.nextLine();
                    List<Book> encontrados = library.searchBookByTitle(title);
                    if (encontrados.isEmpty()) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        library.displayDistancesVerbose(encontrados.get(0));
                    }
                }
                case 23 -> {
                        library.displayRecommendationGraph();
                }
                case 0 -> {
                    LibraryFileManager.saveBooks(livros, library);
                    LibraryFileManager.saveUsers(usuarios);
                    System.out.println("Fechando biblioteca...");
                }
                default -> System.out.println("Opção inválida.");
            }

            if (option != 0) {
                System.out.print("[ENTER] para continuar... ");
                scanner.nextLine();
            }

        } while (option != 0);

        scanner.close();
    }
}