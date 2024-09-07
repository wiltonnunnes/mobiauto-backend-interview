package com.wilton.mobiauto_backend_interview.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

    @Override
    public void initialize(EmailConstraint email) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        return emailField != null && emailField.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");
    }
    
}
