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
│   │   ├── Main.java                        
│   │   ├── model/
│   │   │   ├── Person.java                   
│   │   │   ├── Member.java                   
│   │   │   ├── Librarian.java                
│   │   │   ├── Book.java                     
│   │   │   ├── Reservable.java               
│   │   │   └── Transaction.java                
│   │   ├── service/
│   │   │   ├── Searchable.java               
│   │   │   └── LibraryService.java             
│   │   └── exception/
│   │       ├── BookNotFoundException.java
│   │       ├── MemberNotFoundException.java
│   │       ├── BookNotAvailableException.java
│   │       ├── BorrowingLimitExceededException.java
│   │       └── DuplicateMemberException.java
│   └── test/java/com/library/
│       ├── model/BookTest.java
│       └── service/LibraryServiceTest.java

## Future Enhancements
1. Persist data with a real database (JDBC / Spring Data) instead of in-memory maps
2. Add a console menu or REST API on top of LibraryService
3. Reservation queue for books with zero copies available
4. Configurable loan period / fine rate per genre or member type
5. Email/SMS notifications for due-soon and overdue books
## Author
-Aadarsh Batra 
-25BAI10872
-B.Tech, Computer Science & Engineering (AI & ML)
-VIT Bhopal
