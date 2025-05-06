package com.vinot.somativa1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Representa um livro com cópias físicas/digitais e recomendações.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private String title;
    private String author;
    private int    year;
    private int    bookHash;

    private List<Integer> physicalCopyHashes = new ArrayList<>();
    private List<Integer> digitalCopyHashes  = new ArrayList<>();

    /** HashCodes dos livros recomendados (persistidos em JSON) */
    @JsonProperty("recommendations")
    private List<Integer> recommendations = new ArrayList<>();

    /* ---------- construtores ---------- */
    public Book() { updateBookHash(); }
    public Book(String title)                    { this.title = title; updateBookHash(); }
    public Book(String title, String author)     { this(title); this.author = author; }
    public Book(String title, String author, int year) {
        this(title, author); this.year = year;
    }

    /* ---------- util ---------- */
    public void updateBookHash() { this.bookHash = hashCode(); }
    private int generateCopyHash() {
        return Objects.hash(title, author, year, System.nanoTime());
    }

    /* ---------- getters/setters ---------- */
    public int getBookHash() { return bookHash; }

    public void addPhysicalCopy() { physicalCopyHashes.add(generateCopyHash()); }
    public void addDigitalCopy()  { digitalCopyHashes.add(generateCopyHash()); }

    public List<Integer> getPhysicalCopyHashes() { return physicalCopyHashes; }
    public List<Integer> getDigitalCopyHashes()  { return digitalCopyHashes; }

    public String getTitle()  { return title; }
    public void setTitle(String title) { this.title = title; updateBookHash(); }

    public String getAuthor() { return author; }
    public void setAuthor(String author){ this.author = author; updateBookHash(); }

    public int  getYear() { return year; }
    public void setYear(int year)       { this.year = year; updateBookHash(); }

    public List<Integer> getRecommendations() { return recommendations; }
    public void setRecommendations(List<Integer> recs) { this.recommendations = recs; }

    /* ---------- overrides ---------- */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book b)) return false;
        return year == b.year &&
                Objects.equals(title, b.title) &&
                Objects.equals(author, b.author);
    }
    @Override
    public int hashCode() { return Objects.hash(title, author, year); }

    @Override
    public String toString() {
        return "Book{" + title + " (" + author + ", " + year + ")}";
    }
}
