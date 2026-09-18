package com.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    private static final int MAX_BOOKS_ALLOWED = 3;

    private final String memberId;
    private String name;
    private String email;
    private final List<String> borrowedBookIsbns; 

    public Member(String memberId, String name, String email) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedBookIsbns = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getBorrowedBookIsbns() {
        return Collections.unmodifiableList(borrowedBookIsbns);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean canBorrowMore() {
        return borrowedBookIsbns.size() < MAX_BOOKS_ALLOWED;
    }

    public boolean hasBorrowed(String isbn) {
        return borrowedBookIsbns.contains(isbn);
    }

    void addBorrowedBook(String isbn) {
        borrowedBookIsbns.add(isbn);
    }

    void removeBorrowedBook(String isbn) {
        borrowedBookIsbns.remove(isbn);
    }

    @Override
    public String toString() {
        return String.format("Member[ID=%s, Name='%s', Email='%s', BooksHeld=%d]",
                memberId, name, email, borrowedBookIsbns.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}
