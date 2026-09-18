package com.library;

public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String memberId) {
        super("Member not found with ID: " + memberId);
    }
}
