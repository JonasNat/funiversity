package com.example.funiversity.exceptions;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
