package com.cricketleague;

public class CricketAnalyserException extends RuntimeException {
    enum ExceptionType {
        CRICKET_FILE_PROBLEM,CRICKET_DATA_NOT_FOUND;
    }

    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
