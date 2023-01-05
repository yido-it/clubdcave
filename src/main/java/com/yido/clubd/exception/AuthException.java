package com.yido.clubd.exception;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthException(String msg) {
        super(msg);
    }

    public AuthException() {
        super("권한이 없습니다.");
    }

}
