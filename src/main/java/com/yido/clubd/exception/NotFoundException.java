package com.yido.clubd.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException() {
        super("잘못된 접근");
    }

}
