package com.talentmngmt.exception;

public class CandidateEmployeeExistsException extends RuntimeException{
    public CandidateEmployeeExistsException(Long id){
        super("Candidate-Employee already exists " + id);
    }
}
