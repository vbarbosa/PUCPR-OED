package com.vinot.somativa1.application;

import com.vinot.somativa1.manager.LibraryFileManager;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitlistTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    private static final File TEST_BOOK_FILE = new File("src/test/resources/test-books-waitlist.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/test-users-waitlist.json");

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
    void testFilaDeEsperaCompleta() {
        simulateInput(String.join("\n",
                "7", "UserFila3", "fila3@teste.com", "userFila3", "",
                "1", "Livro Espera", "Autor", "2023", "",
                "16", "userFila3", "Livro Espera", "",
                "17", "Livro Espera", "",
                "0"
        ));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("adicionado à fila de espera"), "Usuário não foi adicionado à fila.");
        assertTrue(out.contains("Fila de espera para 'Livro Espera'"), "Fila não exibida corretamente.");
        assertTrue(out.contains("userFila3"), "Usuário da fila não listado.");
    }
}
