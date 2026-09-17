## Library_Management_System

# FEATURES
1. Book catalogue — add, remove, and search books by title or author (case-insensitive)
2. Member registration — with duplicate-ID protection
3. Issue / Return workflow — enforces copy availability and a per-member borrowing limit (3 books)
4. Overdue fines — automatically calculated from the transaction log (14-day loan period, ₹5/day overdue)
5. Full transaction history — every issue and return is logged with a timestamp
6. Custom exceptions — five checked exceptions instead of null/boolean error handling


# PROJECT STRUCTURE

library-management-system/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/com/library/
│   │   ├── Main.java                          # console demo — runs the full flow once
│   │   ├── model/
│   │   │   ├── Person.java                    # abstract base (id, name, email)
│   │   │   ├── Member.java                    # extends Person
│   │   │   ├── Librarian.java                 # extends Person
│   │   │   ├── Book.java                      # implements Reservable, Comparable
│   │   │   ├── Reservable.java                # interface: hold() / release() / isAvailable()
│   │   │   └── Transaction.java                # issue/return log entry
│   │   ├── service/
│   │   │   ├── Searchable.java                # generic search interface
│   │   │   └── LibraryService.java             # all business logic lives here
│   │   └── exception/
│   │       ├── BookNotFoundException.java
│   │       ├── MemberNotFoundException.java
│   │       ├── BookNotAvailableException.java
│   │       ├── BorrowingLimitExceededException.java
│   │       └── DuplicateMemberException.java
│   └── test/java/com/library/
│       ├── model/BookTest.java
│       └── service/LibraryServiceTest.java
