package com.library;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String isbn) {
        super("Book not found with ISBN: " + isbn);
    }
}
