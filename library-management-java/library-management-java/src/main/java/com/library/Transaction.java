package com.library;

import java.time.LocalDate;

public class Transaction {

    public enum Type {
        ISSUE,
        RETURN
    }

    private static int counter = 1000;

    private final int transactionId;
    private final String isbn;
    private final String memberId;
    private final Type type;
    private final LocalDate date;
    private final LocalDate dueDate; // relevant for ISSUE transactions

    public Transaction(String isbn, String memberId, Type type, LocalDate date, LocalDate dueDate) {
        this.transactionId = ++counter;
        this.isbn = isbn;
        this.memberId = memberId;
        this.type = type;
        this.date = date;
        this.dueDate = dueDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMemberId() {
        return memberId;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return String.format("Txn[#%d, %s, ISBN=%s, Member=%s, Date=%s%s]",
                transactionId, type, isbn, memberId, date,
                dueDate != null ? ", Due=" + dueDate : "");
    }
}
