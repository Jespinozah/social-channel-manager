package com.example.social.manager.exception;

public class SocialManagerErrorResponse {
    private String message;

    public SocialManagerErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
