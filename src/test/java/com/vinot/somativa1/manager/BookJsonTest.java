package com.vinot.somativa1.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinot.somativa1.model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookJsonTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDeserializeBooks() throws Exception {
        File testFile = new File("src/test/resources/test-books.json");
        LinkedList<Book> books = mapper.readValue(testFile, new TypeReference<>() {
        });

        assertEquals(2, books.size());

        Book b1 = books.get(0);
        Book b2 = books.get(1);

        assertEquals("Teste 1", b1.getTitle());
        assertEquals("Autor A", b1.getAuthor());
        assertEquals(2000, b1.getYear());

        assertEquals("Teste 2", b2.getTitle());
        assertEquals("Autor B", b2.getAuthor());
        assertEquals(2022, b2.getYear());
    }
}
