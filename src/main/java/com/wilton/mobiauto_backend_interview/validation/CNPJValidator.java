package com.wilton.mobiauto_backend_interview.validation;

import java.util.regex.*;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNPJValidator implements ConstraintValidator<CNPJConstraint, String> {

    @Override
    public void initialize(CNPJConstraint cnpj) {
    }

    @Override
    public boolean isValid(String cnpjField, ConstraintValidatorContext ctx) {
        if (cnpjField.length() != 14) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]{14}");
        Matcher matcher = pattern.matcher(cnpjField);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return false;
        }

        return true;
    }
    
}
