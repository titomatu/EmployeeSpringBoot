package com.talentmngmt.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Long id) {
        super("Could not find position " + id);
    }
}
