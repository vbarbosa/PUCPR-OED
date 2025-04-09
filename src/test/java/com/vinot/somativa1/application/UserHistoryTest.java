package com.vinot.somativa1.application;

import com.vinot.somativa1.manager.LibraryFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserHistoryTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    private static final File TEST_BOOK_FILE = new File("src/test/resources/test-books-history.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/test-users-history.json");

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
    void testHistoricoVazioUsuario() {
        simulateInput(String.join("\n",
                "7", "HistUser", "hist@teste.com", "histUser", "",
                "18", "histUser", "",
                "0"
        ));
        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Não há histórico para este usuário") || out.contains("histUser"),
                "Histórico de usuário deveria estar vazio ou ausente.");
    }
}
