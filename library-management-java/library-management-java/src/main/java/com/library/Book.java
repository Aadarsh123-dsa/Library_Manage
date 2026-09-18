package com.library;


public class Book {

    private final String isbn;
    private String title;
    private String author;
    private String genre;
    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, String genre, int totalCopies) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        if (totalCopies < 0) {
            throw new IllegalArgumentException("Total copies cannot be negative");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // ---------- Getters ----------
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // ---------- Setters ----------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public void addCopies(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cannot add a negative number of copies");
        }
        this.totalCopies += count;
        this.availableCopies += count;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    void decrementAvailable() {
        if (availableCopies <= 0) {
            throw new IllegalStateException("No available copies to issue for: " + title);
        }
        availableCopies--;
    }

    void incrementAvailable() {
        if (availableCopies >= totalCopies) {
            throw new IllegalStateException("All copies already accounted for: " + title);
        }
        availableCopies++;
    }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title='%s', Author='%s', Genre='%s', Available=%d/%d]",
                isbn, title, author, genre, availableCopies, totalCopies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
