package org.recap.model.userManagement;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by dharmendrag on 7/12/16.
 */
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm userForm=(UserForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty", "Please specify a username.");
        if(userForm.getInstitution()==0)
        {
            errors.rejectValue("institution","error.username.notselected","Please select your institution");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty", "Please specify a password.");

    }
}
