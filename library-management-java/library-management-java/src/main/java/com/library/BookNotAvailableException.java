package com.library;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String title) {
        super("No available copies for book: " + title);
    }
}
