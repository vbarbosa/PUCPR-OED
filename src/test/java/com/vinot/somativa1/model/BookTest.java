package com.vinot.somativa1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    public void testHashCodeConsistency() {
        Book b1 = new Book("1984", "George Orwell", 1949);
        Book b2 = new Book("1984", "George Orwell", 1949);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    public void testAddPhysicalCopyIncrements() {
        Book book = new Book("Title", "Author", 2020);
        int initial = book.getPhysicalCopyHashes().size();
        book.addPhysicalCopy();
        assertEquals(initial + 1, book.getPhysicalCopyHashes().size());
    }

    @Test
    public void testAddDigitalCopyIncrements() {
        Book book = new Book("Title", "Author", 2020);
        int initial = book.getDigitalCopyHashes().size();
        book.addDigitalCopy();
        assertEquals(initial + 1, book.getDigitalCopyHashes().size());
    }
}