package com.vinot.somativa1.application;

import com.vinot.somativa1.manager.LibraryFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookQueueTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    private static final File TEST_BOOK_FILE = new File("src/test/resources/test-books-queue.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/test-users-queue.json");

    @BeforeEach
    void setup() throws IOException {
        LibraryFileManager.overridePaths(TEST_BOOK_FILE, TEST_USER_FILE);
        TEST_BOOK_FILE.getParentFile().mkdirs();
        Files.writeString(TEST_BOOK_FILE.toPath(), "[]", StandardCharsets.UTF_8);
        Files.writeString(TEST_USER_FILE.toPath(), "{}", StandardCharsets.UTF_8);
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
    void testAdicionarUsuarioNaFilaDeEspera() {
        simulateInput(String.join("\n",
                "7", "UserFila", "fila@teste.com", "userFila", "",
                "1", "Livro Fila", "Autor Fila", "2021", "",
                "16", "userFila", "Livro Fila", "",
                "0"
        ));

        Main.main(null);
        String out = output.toString();

        assertTrue(out.contains("adicionado à fila de espera") || out.contains("userFila"),
                "Usuário não foi adicionado à fila de espera corretamente.");
    }

    @Test
    void testVisualizarFilaDeEspera() {
        simulateInput(String.join("\n",
                "7", "UserFila2", "fila2@teste.com", "userFila2", "",
                "1", "Livro Fila2", "Autor Fila2", "2022", "",
                "16", "userFila2", "Livro Fila2", "",
                "17", "Livro Fila2", "",
                "0"
        ));

        Main.main(null);
        String out = output.toString();

        assertTrue(out.contains("Fila de espera para 'Livro Fila2'") || out.contains("userFila2"),
                "Fila de espera não foi exibida corretamente.");
    }
}
