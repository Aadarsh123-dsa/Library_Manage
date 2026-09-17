## Problem Statement 
Design and implement an object-oriented backend model that represents books, members, librarians, and transactions as first-class entities; enforces business rules (availability checks, borrowing limits, fine calculation) consistently through a single service layer; and is verifiable through automated unit tests rather than manual inspection alone.
## Target Audience 
* Students submitting this as a college/academic project on OOP, Java, or software design
* Small libraries or reading rooms (school, college, community) as a starting point before a database or UI is added
* Developers looking for a simple, testable reference example of a layered Java backend with custom exceptions and JUnit tests
 ## High level features 
* Book catalogue — add, remove, and search books by title or author (case-insensitive)
* Member registration — with duplicate-ID protection
* Issue / Return workflow — enforces copy availability and a per-member borrowing limit (3 books)
* Overdue fines — automatically calculated from the transaction log (14-day loan period, ₹5/day overdue)
* Full transaction history — every issue and return is logged with a timestamp
* Custom exceptions — five checked exceptions instead of null/boolean error handling
* Unit tested — 23 JUnit 5 tests covering happy paths, edge cases, and every failure mode

  ## SCOPE
  This project covers the backend object model only — cataloguing books, registering members, issuing/returning books, enforcing a borrowing limit, and calculating overdue fines, all backed by an in-memory data store. It does not include a GUI, a web/REST interface, a database, authentication, or multi-user concurrency handling — those are listed as possible future enhancements, not part of the current implementation.
