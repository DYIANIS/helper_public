package com.shtura.helper.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shtura.helper.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext context) {
        if (user == null) {
            return true;
        }
        boolean isValid = user.getPassword().equals(user.getMatchingPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords don't match").addPropertyNode("matchingPassword")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
