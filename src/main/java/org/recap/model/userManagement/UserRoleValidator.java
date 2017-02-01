package org.recap.model.userManagement;

import org.apache.commons.lang3.StringUtils;
import org.recap.model.userManagement.UserRoleForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dharmendrag on 30/12/16.
 */
public class UserRoleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRoleForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRoleForm userRoleForm=(UserRoleForm)target;
        if(userRoleForm.isCreatedRequest())
        {
            if(StringUtils.isBlank(userRoleForm.getNetworkLoginId()) )
            {
                errors.rejectValue("networkLoginId","networkLoginId.blank","Please provide Network Login Id");
            }else{

            }
        }

    }
}
