package com.wilton.mobiauto_backend_interview.dto;

public class ErrorDTO {
    
    private String message;

    private String field;

    public ErrorDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
