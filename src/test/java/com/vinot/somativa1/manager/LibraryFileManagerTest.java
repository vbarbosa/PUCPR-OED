package com.vinot.somativa1.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryFileManagerTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File TEST_BOOK_FILE = new File("src/test/resources/lib-test-books.json");
    private static final File TEST_USER_FILE = new File("src/test/resources/lib-test-users.json");

    @Test
    public void testSaveAndLoadBooks() {
        LinkedList<Book> books = new LinkedList<>();
        books.add(new Book("Teste 1", "Autor A", 2000));
        books.add(new Book("Teste 2", "Autor B", 2022));

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(TEST_BOOK_FILE, books);
            LinkedList<Book> loaded = mapper.readValue(TEST_BOOK_FILE,
                    mapper.getTypeFactory().constructCollectionType(LinkedList.class, Book.class));
            assertEquals(2, loaded.size());
            assertEquals("Teste 1", loaded.get(0).getTitle());
        } catch (Exception e) {
            fail("Erro ao salvar ou carregar livros de teste: " + e.getMessage());
        } finally {
            TEST_BOOK_FILE.delete();
        }
    }

    @Test
    public void testSaveAndLoadUsers() {
        Map<String, User> users = new HashMap<>();
        users.put("teste1", new User("Usuário Teste 1", "teste1@email.com", "teste1"));
        users.put("teste2", new User("Usuário Teste 2", "teste2@email.com", "teste2"));

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(TEST_USER_FILE, users);
            Map<String, User> loaded = mapper.readValue(TEST_USER_FILE,
                    mapper.getTypeFactory().constructMapType(HashMap.class, String.class, User.class));
            assertTrue(loaded.containsKey("teste1"));
            assertEquals("Usuário Teste 1", loaded.get("teste1").getName());
        } catch (Exception e) {
            fail("Erro ao salvar ou carregar usuários de teste: " + e.getMessage());
        } finally {
            TEST_USER_FILE.delete();
        }
    }

    @Test
    public void testFilesExistAfterSave() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(TEST_BOOK_FILE, new LinkedList<>());
            mapper.writerWithDefaultPrettyPrinter().writeValue(TEST_USER_FILE, new HashMap<>());
            assertTrue(TEST_BOOK_FILE.exists());
            assertTrue(TEST_USER_FILE.exists());
        } catch (Exception e) {
            fail("Erro ao criar arquivos de teste.");
        } finally {
            TEST_BOOK_FILE.delete();
            TEST_USER_FILE.delete();
        }
    }
}
