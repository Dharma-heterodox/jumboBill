/**
 * Created by hemalathas on 22/12/16.
 */
jQuery(document).ready(function ($) {

    $('#createRoleLink').click(function(){
        populatePermissionNames();
    });

    $("#recaprole .backtext a").click(function () {
        $("#recaprole .create-role-section").hide();
        $("#recaprole .roles-main-section").show();
        $("#recaprole .delete-role-section").hide();
        $("#recaprole .edit-role-section").hide();
        $('#errorMessageId').hide();
        $("#recaprole .errorMessage").hide();
    });

    $('#permissionNameId').multiselect();
    $('#deletePermissionNameId').multiselect();
    $('#editPermissionNameId').multiselect();

    $('#roleNameErrorMessage').hide();
    $('#roleDescriptionErrorMessage').hide();
    $('#permissionNamesErrorMessage').hide();
    $('#editRoleNameErrorMessage').hide();
    $('#editRoleDescriptionErrorMessage').hide();
    $('#editPermissionNamesErrorMessage').hide();

});

function loadCreateRole() {
    var $form = $('#roles-form');
    var url = $form.attr('action') + "?action=loadCreateRole";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#rolesContentId').html(response);
            $("#recaprole .create-role-section").show();
            $("#recaprole .roles-main-section").hide();
            $("#recaprole .delete-role-section").hide();
            $("#recaprole .edit-role-section").hide();
        }
    });
}

function populatePermissionNames(){
    var $form = $('#roles-form');
    var url = $form.attr('action') + "?action=populatePermissionName";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#rolesContentId').html(response);
            $("#recaprole .create-role-section").show();
            $("#recaprole .roles-main-section").hide();
            $("#recaprole .delete-role-section").hide();
            $("#recaprole .edit-role-section").hide();
        }
    });

}

function editResetDefault() {
    var $form = $('#roles-form');
    var url = $form.attr('action') + "?action=editClearPage";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#rolesContentId').html(response);
            $("#recaprole .roles-main-section").hide();
            $('#roleNameErrorMessage').hide();
            $('#roleDescriptionErrorMessage').hide();
            $('#permissionNamesErrorMessage').hide();
            $('#editRoleNameErrorMessage').hide();
            $('#editRoleDescriptionErrorMessage').hide();
            $('#editPermissionNamesErrorMessage').hide();
            $("#recaprole .edit-role-section").show();
            $("#recaprole .create-role-section").hide();
            $("#recaprole .delete-role-section").hide();
            $('#editPermissionNameId').multiselect();
        }
    });
}

function createResetDefault() {
    var $form = $('#roles-form');
    var url = $form.attr('action') + "?action=clearPage";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#rolesContentId').html(response);
            $("#recaprole .create-role-section").show();
            $('#roleNameErrorMessage').hide();
            $('#roleDescriptionErrorMessage').hide();
            $('#permissionNamesErrorMessage').hide();
            $("#recaprole .edit-role-section").hide();
            $("#recaprole .roles-main-section").hide();
            $("#recaprole .delete-role-section").hide();

        }
    });
}

function createRole(){
    if(isValidRole()){
        loadCreateRole();
    }
}

function editRole(roleId,roleNames,roleDescription,permissionNames){
    console.log('Role Name  : ' + roleNames);
    console.log('Role Desc  : ' + roleDescription);
    console.log('Permission Name  : ' + permissionNames);
    var $form = $('#roles-form');
    var roleName = $('#recaprole .roleDetails').val();
    var url = $form.attr('action') + "?action=editRole";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: {roleId:roleId,roleName:roleNames,roleDescription:roleDescription,permissionName:permissionNames},
        success: function (response) {
            $('#rolesContentId').html(response);
            console.log(response);
            $("#recaprole .edit-role-section").show();
            $("#recaprole .roles-main-section").hide();
            $("#recaprole .create-role-section").hide();
            $("#recaprole .delete-role-section").hide();
        }
    });
    
}

function saveEditedRole(){
    var editValidation = isValidEdit();
    if(editValidation == true){
        var roleId = $('#roleId').val();
        var permissionNames = $('#editPermissionNameId').val();
        var roleName = $('#editRoleName').val();
        var roleDescription = $('#editRoleDescription').val();
        var $form = $('#roles-form');
        var url = $form.attr('action') + "?action=saveEditedRole";
        var role = $.ajax({
            url: url,
            type: 'post',
            data:{roleId:roleId,permissionNames:permissionNames,roleName:roleName,roleDescription:roleDescription},
            success: function (response) {
                console.log("completed");
                $('#rolesContentId').html(response);
                $('#roleNameId').val('');
                $("#recaprole .edit-role-section").show();
                $("#recaprole .create-role-section").hide();
                $("#recaprole .roles-main-section").hide();
                $("#recaprole .delete-role-section").hide();
            }
        });
    }
}

function deleteFromDb(){
    var pageNumber = $('#afterDelPageNumber').val();
    var totalPageCount = $('#afterDelTotalPageCount').val();
    var pageSize = $('#afterDelPageSize').val();
    var $form = $('#roles-form');
    var url = $form.attr('action') + "?action=delete";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#rolesContentId').html(response);
            $("#recaprole .edit-role-section").hide();
            $("#recaprole .create-role-section").hide();
            $("#recaprole .roles-main-section").show();
            $("#recaprole .search-results-container").show();
            $("#recaprole .delete-role-section").hide();
        }
    });
}

function deleteRole(roleId,roleNames,roleDescription,permissionNames) {
    console.log('Role Name  : ' + roleNames);
    console.log('Role Desc  : ' + roleDescription);
    console.log('Permission Name  : ' + permissionNames);

    var pageSize = $('#numOfRecordsId').val();
    var pageNumber = $('#pageNumber').val();
    var totalPageCount = $('#totalPageCount').val();

    var $form = $('#roles-form');
    var roleName = $('#recaprole .roleDetails').val();
    var url = $form.attr('action') + "?action=deleteRole";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: {roleId:roleId,roleName:roleNames,roleDescription:roleDescription,permissionName:permissionNames,pageSize:pageSize,pageNumber:pageNumber,totalPageCount:totalPageCount},
        success: function (response) {
            $('#rolesContentId').html(response);
            $("#recaprole .delete-role-section").show();
            $("#recaprole .create-role-section").hide();
            $("#recaprole .roles-main-section").hide();
            $("#recaprole .edit-role-section").hide();
            scrollTo(0,0);
        }
    });
    
}

function isValidRole() {
    var isValid = true;
    var roleName = $('#roleNameId').val();
    var roleDescription = $('#roleDescriptionId').val();
    var permissionName = $('#permissionNameId').val();
    console.log(permissionName);

    if (isBlankValue(roleName)) {
        $('#roleNameErrorMessage').show();
        isValid = false;
    } else {
        $('#roleNameErrorMessage').hide();
    }
    if (isBlankValue(roleDescription)) {
        $('#roleDescriptionErrorMessage').show();
        isValid = false;
    } else {
        $('#roleDescriptionErrorMessage').hide();
    }
    if (isBlankValue(permissionName)) {
        $('#permissionNamesErrorMessage').show();
        isValid = false;
    } else {
        $('#permissionNamesErrorMessage').hide();
    }
    return isValid;
}

function isValidEdit(){
    var isValid = true;
    var roleName = $('#editRoleName').val();
    var roleDescription = $('#editRoleDescription').val();
    var permissionName = $('#editPermissionNameId').val();
    if (isBlankValue(roleName)) {
        $('#editRoleNameErrorMessage').show();
        isValid = false;
    } else {
        $('#editRoleNameErrorMessage').hide();
    }
    if (isBlankValue(roleDescription)) {
        $('#editRoleDescriptionErrorMessage').show();
        isValid = false;
    } else {
        $('#editRoleDescriptionErrorMessage').hide();
    }
    if (isBlankValue(permissionName)) {
        $('#editPermissionNamesErrorMessage').show();
        isValid = false;
    } else {
        $('#editPermissionNamesErrorMessage').hide();
    }
    return isValid;

}


function isBlankValue(value) {
    if (value == null || value == '') {
        return true;
    }
    return false;
}


function toggleRoleNameValidation() {
    var roleNameId = $('#roleNameId').val();
    if (isBlankValue(roleNameId)) {
        $('#roleNameErrorMessage').show();
    } else {
        $('#roleNameErrorMessage').hide();
    }
}

function toggleRoleDescriptionValidation() {
    var roleDescriptionId = $('#roleDescriptionId').val();
    if (isBlankValue(roleDescriptionId)) {
        $('#roleDescriptionErrorMessage').show();
    } else {
        $('#roleDescriptionErrorMessage').hide();
    }
}

function togglePermissionValidation() {
    var permissionNameId = $('#permissionNameId').val();
    if (isBlankValue(permissionNameId)) {
        $('#permissionNamesErrorMessage').show();
    } else {
        $('#permissionNamesErrorMessage').hide();
    }
}