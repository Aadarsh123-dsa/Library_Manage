package com.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0; 

    private final Map<String, Book> books;       
    private final Map<String, Member> members;     
    private final List<Transaction> transactions;

    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public Book addBook(String isbn, String title, String author, String genre, int copies) {
        if (books.containsKey(isbn)) {
            Book existing = books.get(isbn);
            existing.addCopies(copies);
            return existing;
        }
        Book book = new Book(isbn, title, author, genre, copies);
        books.put(isbn, book);
        return book;
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        books.remove(isbn);
    }

    public Book findBookByIsbn(String isbn) throws BookNotFoundException {
        Book book = books.get(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        }
        return book;
    }

    public List<Book> searchByTitle(String keyword) {
        String lower = keyword.toLowerCase();
        return books.values().stream()
                .filter(b -> b.getTitle() != null && b.getTitle().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String keyword) {
        String lower = keyword.toLowerCase();
        return books.values().stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Member registerMember(String memberId, String name, String email) {
        Member member = new Member(memberId, name, email);
        members.put(memberId, member);
        return member;
    }

    public void removeMember(String memberId) throws MemberNotFoundException {
        if (!members.containsKey(memberId)) {
            throw new MemberNotFoundException(memberId);
        }
        members.remove(memberId);
    }

    public Member findMemberById(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException(memberId);
        }
        return member;
    }

    public Collection<Member> getAllMembers() {
        return members.values();
    }

    public Transaction issueBook(String isbn, String memberId)
            throws BookNotFoundException, MemberNotFoundException,
                   BookNotAvailableException, BorrowingLimitExceededException {

        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(book.getTitle());
        }
        if (!member.canBorrowMore()) {
            throw new BorrowingLimitExceededException(memberId);
        }

        book.decrementAvailable();
        member.addBorrowedBook(isbn);

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(LOAN_PERIOD_DAYS);
        Transaction txn = new Transaction(isbn, memberId, Transaction.Type.ISSUE, issueDate, dueDate);
        transactions.add(txn);
        return txn;
    }

    public double returnBook(String isbn, String memberId)
            throws BookNotFoundException, MemberNotFoundException {

        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (!member.hasBorrowed(isbn)) {
            throw new IllegalStateException(
                    "Member " + memberId + " has not borrowed book " + isbn);
        }

        book.incrementAvailable();
        member.removeBorrowedBook(isbn);

        LocalDate returnDate = LocalDate.now();
        Transaction returnTxn = new Transaction(isbn, memberId, Transaction.Type.RETURN, returnDate, null);
        transactions.add(returnTxn);

        double fine = calculateFine(isbn, memberId, returnDate);
        return fine;
    }

    private double calculateFine(String isbn, String memberId, LocalDate returnDate) {
        Transaction lastIssue = null;
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getType() == Transaction.Type.ISSUE
                    && t.getIsbn().equals(isbn)
                    && t.getMemberId().equals(memberId)) {
                lastIssue = t;
                break;
            }
        }
        if (lastIssue == null || lastIssue.getDueDate() == null) {
            return 0.0;
        }
        long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(lastIssue.getDueDate(), returnDate);
        if (overdueDays <= 0) {
            return 0.0;
        }
        return overdueDays * FINE_PER_DAY;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> getTransactionsForMember(String memberId) {
        return transactions.stream()
                .filter(t -> t.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public int getTotalBookCount() {
        return books.size();
    }

    public int getTotalMemberCount() {
        return members.size();
    }
}
