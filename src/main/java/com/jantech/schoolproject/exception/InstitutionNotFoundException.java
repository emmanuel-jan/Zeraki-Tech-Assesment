package com.jantech.schoolproject.exception;

public class InstitutionNotFoundException extends RuntimeException {
    public InstitutionNotFoundException(Long id){
        super("Could not find the institution with id "+ id);
    }
}
