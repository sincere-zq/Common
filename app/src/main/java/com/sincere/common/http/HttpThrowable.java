package com.sincere.common.http;

public class HttpThrowable extends Throwable {
    public String message;

    public HttpThrowable(String message) {
        super(message);
        this.message = message;
    }
}
