package com.haui.demo.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) return true;
        return isVietnamPhoneNumber(phoneNumber);
    }

    private boolean isVietnamPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(03[^01]|04[8]|05[2689]|07[06789]|08[^0]|09[0-9])[0-9]{7}$");
        return pattern.matcher(phoneNumber).matches();
    }
}
