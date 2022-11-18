package com.example.funiversity.exceptions;

public class ProfessorAlreadyExistsException extends RuntimeException{
    public ProfessorAlreadyExistsException(String message) {
        super(message);
    }
}
