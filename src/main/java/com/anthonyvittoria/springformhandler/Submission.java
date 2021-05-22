package com.anthonyvittoria.springformhandler;

public class Submission {
    private final String name;
    private final String email;
    private final String message;

    public Submission(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
