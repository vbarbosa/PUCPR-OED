package com.vinot.somativa1.application;

import com.vinot.somativa1.manager.LibraryFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    private static final File TEST_BOOK_FILE = new File("src/test/resources/test-books-menu.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/test-users-menu.json");

    @BeforeEach
    void setup() throws IOException {
        // Redireciona os arquivos de teste
        LibraryFileManager.overridePaths(TEST_BOOK_FILE, TEST_USER_FILE);
        TEST_BOOK_FILE.getParentFile().mkdirs();

        // Preenche os arquivos com JSON válido
        Files.writeString(TEST_BOOK_FILE.toPath(), "[]", StandardCharsets.UTF_8);  // Lista vazia
        Files.writeString(TEST_USER_FILE.toPath(), "{}", StandardCharsets.UTF_8);  // Mapa vazio
    }

    @AfterEach
    void restoreIO() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void testAddBookAndExit() {
        simulateInput(String.join("\n", "1", "Livro Teste", "Autor", "2000", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Livro adicionado"), "Saída esperada não encontrada");
    }

    @Test
    void testListBooksAndExit() {
        simulateInput(String.join("\n", "1", "Livro Teste", "Autor", "2000", "", "2", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Livro") || out.contains("title="), "Deveria listar livros");
    }

    @Test
    void testAddUserAndExit() {
        simulateInput(String.join("\n", "7", "Usuário Teste", "teste@teste.com", "usuario123", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Usuário") || out.contains("userId"), "Usuário não adicionado corretamente");
    }

    @Test
    void testListUsersAndExit() {
        simulateInput(String.join("\n", "7", "Usuário Teste", "teste@teste.com", "usuario123", "", "8", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("userId") || out.contains("Usuário"), "Usuários não foram listados");
    }

    @Test
    void testSearchBookAndExit() {
        simulateInput(String.join("\n", "1", "Livro de Teste", "Autor", "2000", "", "3", "Teste", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Teste") || out.contains("Livro"), "Livro de teste não foi encontrado");
    }

    @Test
    void testInventoryViewAndExit() {
        simulateInput(String.join("\n", "6", "", "0"));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Inventário") || out.contains("ObraHash"), "Inventário não exibido corretamente");
    }
}
