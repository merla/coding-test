package com.occ.namescore.exception;

public class NameScoreException extends RuntimeException{

    public NameScoreException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
