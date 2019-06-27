package com.innowise.employeemodule.entity;

public class RestResponse {
    private String message;

    public RestResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
