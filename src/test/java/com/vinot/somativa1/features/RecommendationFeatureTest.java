package com.vinot.somativa1.features;

import com.vinot.somativa1.application.Main;
import com.vinot.somativa1.manager.LibraryFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationFeatureTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    private static final File TEST_BOOK_FILE = new File("src/test/resources/test-books-recommendations.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/test-users-recommendations.json");

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
    void testRecommendationGraphPersistence() {
        simulateInput(String.join("\n",
                "1", "Livro A", "Autor A", "2000", "",
                "1", "Livro B", "Autor B", "2001", "",
                "20", "Livro A", "Livro B", "",
                "15", "",
                "0"
        ));

        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Recomendacao") || out.contains("adicionada"), "Falha ao persistir grafo de recomendacoes.");
    }

    @Test
    void testRecommendationView() {
        simulateInput(String.join("\n",
                "1", "Livro A", "Autor A", "2000", "",
                "1", "Livro B", "Autor B", "2001", "",
                "20", "Livro A", "Livro B", "",
                "19", "Livro A", "",
                "0"
        ));

        Main.main(null);
        String out = output.toString();
        assertTrue(out.contains("Livro B"), "Recomendacoes nao foram exibidas corretamente.");
    }
}
