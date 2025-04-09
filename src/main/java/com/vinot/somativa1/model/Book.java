package com.vinot.somativa1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Representa um livro com título, autor, ano, categoria e identificador único via hash.
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private int bookHash;
    private List<Integer> physicalCopyHashes = new ArrayList<>();
    private List<Integer> digitalCopyHashes = new ArrayList<>();

    public Book() {
        updateBookHash();
    }

    public Book(String title) {
        this.title = title;
        updateBookHash();
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        updateBookHash();
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        updateBookHash();
    }

    public void updateBookHash() {
        this.bookHash = hashCode();
    }

    /**
     * Retorna um livro com base no hash, se encontrado.
     * @return livro encontrado ou vazio
     */
    public int getBookHash() {
        return bookHash;
    }

    public void addPhysicalCopy() {
        physicalCopyHashes.add(generateCopyHash());
    }

    public void addDigitalCopy() {
        digitalCopyHashes.add(generateCopyHash());
    }

    private int generateCopyHash() {
        return Objects.hash(title, author, year, System.nanoTime());
    }

    public List<Integer> getPhysicalCopyHashes() {
        return physicalCopyHashes;
    }

    public List<Integer> getDigitalCopyHashes() {
        return digitalCopyHashes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateBookHash();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        updateBookHash();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
        updateBookHash();
    }

    public void setPhysicalCopyHashes(List<Integer> physicalCopyHashes) {
        this.physicalCopyHashes = physicalCopyHashes;
    }

    public void setDigitalCopyHashes(List<Integer> digitalCopyHashes) {
        this.digitalCopyHashes = digitalCopyHashes;
    }

    @Override
    public String toString() {
        return "model.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", bookHash=" + bookHash +
                ", físicos=" + physicalCopyHashes.size() +
                ", digitais=" + digitalCopyHashes.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return getYear() == book.getYear() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getYear());
    }
}
