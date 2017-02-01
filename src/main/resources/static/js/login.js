/**
 * Created by rajeshbabuk on 29/12/16.
 */
function toggleUsernameValidation() {
    var username = $('#networkloginid').val();
    if (isBlankValue(username)) {
        $('#userNameError').show();
    } else {
        $('#userNameError').hide();
        $('#wrongCredentialsError').hide();
    }
}

function togglePasswordValidation() {
    var username = $('#loginpassword').val();
    if (isBlankValue(username)) {
        $('#passwordError').show();
    } else {
        $('#passwordError').hide();
        $('#wrongCredentialsError').hide();
    }
}

function toggleInstitutionValidation() {
    var institution = $('#institution').val();
    if (isBlankValue(institution)) {
        $('#institutionError').show();
    } else {
        $('#institutionError').hide();
        $('#wrongCredentialsError').hide();
    }
}

function isBlankValue(value) {
    if (value == null || value == '') {
        return true;
    }
    return false;
}  