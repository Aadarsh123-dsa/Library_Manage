package com.library;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("978-0134685991", "Effective Java", "Joshua Bloch", "Programming", 3);
        library.addBook("978-0132350884", "Clean Code", "Robert C. Martin", "Programming", 2);
        library.addBook("978-0596009205", "Head First Design Patterns", "Freeman & Robson", "Programming", 1);

        library.registerMember("M001", "Aadarsh", "aadarsh@example.com");
        library.registerMember("M002", "Riya", "riya@example.com");

        System.out.println("=== Initial Catalog ===");
        library.getAllBooks().forEach(System.out::println);

        try {
            System.out.println("\n=== Issuing Books ===");
            Transaction t1 = library.issueBook("978-0134685991", "M001");
            System.out.println("Issued: " + t1);

            Transaction t2 = library.issueBook("978-0596009205", "M002");
            System.out.println("Issued: " + t2);

            try {
                library.issueBook("978-0596009205", "M001");
            } catch (BookNotAvailableException e) {
                System.out.println("Expected failure: " + e.getMessage());
            }

            System.out.println("\n=== Catalog After Issuing ===");
            library.getAllBooks().forEach(System.out::println);

            System.out.println("\n=== Returning a Book ===");
            double fine = library.returnBook("978-0134685991", "M001");
            System.out.println("Book returned. Fine due: " + fine);

            System.out.println("\n=== Transaction History ===");
            library.getTransactionHistory().forEach(System.out::println);

            System.out.println("\n=== Search ===");
            System.out.println("Search 'clean': " + library.searchByTitle("clean"));
            System.out.println("Search author 'martin': " + library.searchByAuthor("martin"));

        } catch (BookNotFoundException | MemberNotFoundException
                 | BookNotAvailableException | BorrowingLimitExceededException e) {
            System.err.println("Operation failed: " + e.getMessage());
        }
    }
}
