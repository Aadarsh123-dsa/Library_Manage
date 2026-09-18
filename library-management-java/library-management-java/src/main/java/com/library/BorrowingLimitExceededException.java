package com.library;

public class BorrowingLimitExceededException extends Exception {
    public BorrowingLimitExceededException(String memberId) {
        super("Member " + memberId + " has reached the maximum borrowing limit");
    }
}
