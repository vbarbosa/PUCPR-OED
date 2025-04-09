package com.vinot.somativa1.controller;

import com.vinot.somativa1.model.Book;
import com.vinot.somativa1.model.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddAndSearchBook() {
        Library library = new Library();
        Book book = new Book("Java", "Oracle", 2022);
        library.addBook(book);
        assertFalse(library.searchBookByTitle("Java").isEmpty());
    }

    @Test
    public void testAddAndRetrieveUser() {
        Library library = new Library();
        library.addUser("Leo", "leo@email.com", "leo1");
        Optional<User> user = library.getUser("leo1");
        assertTrue(user.isPresent());
        assertEquals("Leo", user.get().getName());
    }

    @Test
    public void testRemoveUser() {
        Library library = new Library();
        library.addUser("Leo", "leo@email.com", "leo1");
        library.removeUser("leo1");
        assertTrue(library.getUser("leo1").isEmpty());
    }
}