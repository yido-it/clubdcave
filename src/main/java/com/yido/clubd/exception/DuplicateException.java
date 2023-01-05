package com.yido.clubd.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String msg) {
        super(msg);
    }

    public DuplicateException() {
        super("Duplicated");
    }
}
