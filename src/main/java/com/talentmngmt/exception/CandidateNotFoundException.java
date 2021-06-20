package com.talentmngmt.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(Long id) {
        super("Could not find candidate " + id);
    }
}
