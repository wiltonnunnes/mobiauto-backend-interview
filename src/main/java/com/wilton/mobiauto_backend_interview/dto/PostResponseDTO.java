package com.wilton.mobiauto_backend_interview.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PostResponseDTO {
    
    private String message;

    @JsonInclude(Include.NON_NULL)
    private List<ErrorDTO> errors;

    public PostResponseDTO(String message, List<ErrorDTO> errors) {
        this.message = message;
        this.errors = errors;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
