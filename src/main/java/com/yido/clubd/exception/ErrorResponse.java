package com.yido.clubd.exception;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "error")
public class ErrorResponse {
    private String msg;
    private String code;

    public ErrorResponse() {
    }

    public ErrorResponse(String msg) {
        this.msg = msg;
    }

    public ErrorResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
