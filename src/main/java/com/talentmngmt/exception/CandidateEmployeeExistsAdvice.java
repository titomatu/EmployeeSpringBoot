package com.talentmngmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CandidateEmployeeExistsAdvice {
    @ExceptionHandler(CandidateEmployeeExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String CandidateEmployeeExistsHandler(CandidateEmployeeExistsException ex){
        return ex.getMessage();
    }
}
