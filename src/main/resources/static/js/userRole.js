jQuery(document).ready(function ($) {
    $('#createRequest').click(function () {
        $('#networkLoginId').val('');
        $('#institutionId').val(1);
        $('#roleId').multiselect('deselectAll', false);
        $('#roleId').multiselect('refresh');
        $('#userDescription').val('');
        $('#successMsg').hide();
        $('#errormsg').hide();
    });

    $('#reset').click(function () {
        $('#networkLoginId').val('');
        $('#institutionId').val('');
        $('#userDescription').val('');
        $('#roleId').multiselect('deselectAll', false);
        $('#roleId').multiselect('refresh');
        $('#emailId').val('');
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
        $('#successMsg').hide();

    });

    $('#editusers-createview').hide();
    $('#deleteUsers-view').hide();
    $('#users-createview').hide();

    $('#backLink').click(function () {
        $('#successMsg').hide();
        $('#failureMsg').hide();
        $('#emailIdfailureMsg').hide();
        $('#editusers-createview').hide();
        $('#request-result-table').hide();
        $('#userRolePaginationDivId').hide();
        $('#showEntries').hide();
        $('#deleteUsers-view').hide();
        $('#numOfRecordsId').val(10);
        $('#pageNumber').val(0);
    });

    $('#editbackLink').click(function () {
        $('#successMsg').hide();
        $('#failureMsg').hide();
        $('#emailIdfailureMsg').hide();
        $('#editusers-createview').hide();
        $('#request-result-table').hide();
        $('#userRolePaginationDivId').hide();
        $('#showEntries').hide();
        $('#deleteUsers-view').hide();
        $('#numOfRecordsId').val(10);
        $('#pageNumber').val(0);
    });

    $('#deletebackLink').click(function () {
        $('#successMsg').hide();
        $('#failureMsg').hide();
        $('#emailIdfailureMsg').hide();
        $('#editusers-createview').hide();
        $('#request-result-table').hide();
        $('#userRolePaginationDivId').hide();
        $('#showEntries').hide();
        $('#deleteUsers-view').hide();
        $('#numOfRecordsId').val(10);
        $('#pageNumber').val(0);
    });

    /***Role Tab Form Show/Hide ***/
    $("#recaprole a.role-detailtable").click(function (e) {
        $("#role-searchview").hide();
        $("#role-detailtableview").show();
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
    });
    $("#recaprole .rolebacklink a").click(function (e) {
        $("#role-detailtableview").hide();
        $("#role-searchview").show();
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
    });
    /***Users Tab Form Show/Hide ***/
    $("#recapusers .userscreaterequestlink a").click(function (e) {
        $('#networkLoginId').val('');
        $('#institutionId').val('');
        $('#userDescription').val('');
        $('#roleId').multiselect('deselectAll', false);
        $('#roleId').multiselect('refresh');
        $('#emailId').val('');
        $("#users-searchview").hide();
        $("#users-createview").show();
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
    });
    $("#recapusers .usersbacklink a").click(function (e) {
        $("#users-createview").hide();
        $("#users-searchview").show();
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
    });

    $("#userRoles .users-main-section").show();
    resetDefaults();
    /***Request Tab Create Request Form Show/Hide ***/
    $("#userRoles .usersbacklink a").click(function (e) {
        $("#userRoles .users-main-section").show();
        $("#userRoles .user-create-section").hide();

    });
    $("#userRoles .user-create a").click(function () {
        $("#userRoles .users-main-section").hide();
        $("#userRoles .user-create-section").show();

    });


    /**Multi Select **/
    $('#roleId').multiselect();
    $('#recapusers #userstabaddrole').multiselect();
    $('#editroleId').multiselect();

    //Refresh modal data
    $('.modal').on('show.bs.modal', function (event) {
        console.log(this.id);
        refresh:true;
        $(this).removeData();
    });
});
function userRolesFirstPage() {
    //searchUserRoles('first','#userRoles .users-main-section','#userRoles .user-create-section');
    var $form = $('#userRole-form');
    var url = "/userRoles/first";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('.showEntriesOption').show();
            $('#request-result-table').html(response);
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();

        }
    });
}

function userRolesLastPage() {
    //searchUserRoles('last','#userRoles .users-main-section','#userRoles .user-create-section');
    $('#pageNumber').val(parseInt($('#totalPageCount').val()) - 1);
    var $form = $('#userRole-form');
    var url = "/userRoles/last";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('#request-result-table').html(response);
            $('.showEntriesOption').show();
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();

        }
    });
}

function userRolesPreviousPage() {
    $('#pageNumber').val(parseInt($('#pageNumber').val()) - 1);
    //searchUserRoles('previous','#userRoles .users-main-section','#userRoles .user-create-section');
    var $form = $('#userRole-form');
    var url = "/userRoles/previous";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('#request-result-table').html(response);
            $('.showEntriesOption').show();
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();

        }
    });
}

function userRolesNextPage() {
    $('#pageNumber').val(parseInt($('#pageNumber').val()) + 1);
    //searchUserRoles('next','#userRoles .users-main-section','#userRoles .user-create-section');
    var $form = $('#userRole-form');
    var url = "/userRoles/next";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('#request-result-table').html(response);
            $('#showEntries').show();
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();

        }
    });
}

function deleteUserRole(networkLoginId, userId) {
    console.log('DeleteUserRole');
    console.log('NetWorkLoginId : ' + networkLoginId);
    console.log('UserId  : ' + userId);
    var pagesize = $('#numOfRecordsId').val();
    var pageNumber = $('#pageNumber').val();
    var totalPageCount = $('#totalPageCount').val();
    var $form = $('#userRole-form');
    var url = "/userRoles/deleteUser";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: {networkLoginId: networkLoginId, userId: userId,pagesize:pagesize,pageNumber:pageNumber,totalPageCount :totalPageCount },
        success: function (response) {
            console.log("completed");
            $('#userRolesContentId').html(response);
            $('#deleteUsers-view').show();
            $('#users-searchview').hide();
            $('#users-createview').hide();
            $('#successMsg').show();
            $('#deletedRoleId').multiselect();
        }
    });
}

//Delete User Id
function deleteUser() {
    console.log('Delete');
    var userId = $('#editUserIdHidden').val();
    var networkLoginId = $('#deleteNetworkLoginId').val();
    var pageNumber = $('#afterDelPageNumber').val();
    var totalPageCount = $('#afterDelTotalPageCount').val();
    var pageSize = $('#afterDelPageSize').val();
    var $form = $('#userRole-form');
    var url = "/userRoles/delete";
    var role = $.ajax({
        url: url,
        type: 'post',
        data: {userId: userId,networkLoginId:networkLoginId,pageNumber:pageNumber,totalPageCount:totalPageCount,pageSize:pageSize},
        success: function (response) {
            console.log("completed");
            $('#userRolesContentId').html(response);
            $('#showEntries').show();
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();
        }
    });
}

//EditUser
function editUser(userId, networkLoginId) {
    console.log("userId" + userId);
    console.log("log id" + networkLoginId);
    $('#editsuccessMsg').hide();
    $('#editerrormsg').hide();
    $('#editusers-createview').show();
    $('#users-createview').hide();
    $('#users-searchview').hide();
    var url = "/userRoles/editUser";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: {userId: userId, networkLoginId: networkLoginId},
        success: function (response) {
            $('#userRolesContentId').html(response);
            $('#editusers-createview').show();
            $('#users-searchview').hide();
            $('#users-createview').hide();
            $('#successMsg').show();
            $('#editroleId').multiselect();
        }
    });
}

function editclear() {
    $('#editsuccessMsg').hide();
    editUser(userId, networkLoginId);
}


function editUserDetails() {
        var userId = $('#editUserId').val();
        var networkLoginId = $('#editnetworkLoginId').val();
        var institutionId = $('#editinstitutionId').val();
        var roleIds = $('#editroleId').val();
        var userDescription = $('#edituserDescription').val();
        var userEmailId = $('#editEmailId').val();
        $('#editusers-createview').show();
        $('#users-createview').hide();
        $('#users-searchview').hide();
        var $form = $('#userRole-form');
        var url = "/userRoles/saveEditUserDetails";
        var request = $.ajax({
            url: url,
            type: 'post',
            data: {
                userId: userId,
                networkLoginId: networkLoginId,
                roleIds: roleIds,
                userDescription: userDescription,
                institutionId: institutionId,
                userEmailId: userEmailId
            },
            success: function (response) {
                $('#userRolesContentId').html(response);
                $('#editusers-createview').show();
                $('#users-searchview').hide();
                $('#users-createview').hide();
                $('#successMsg').show();
                $('#editroleId').multiselect();
            }
        });
}


function searchUserRoles() {
    var $form = $('#userRole-form');
    var url = "/userRoles/searchUsers";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('#request-result-table').html(response);
            $('#showEntries').show();
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();
        }
    });
}


function showEntriesChange() {
    $('#pageNumber').val(0);
    var $form = $('#userRole-form');
    var url = "/userRoles/searchUsers";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            $('#request-result-table').html(response);
            $('#request-result-table').show();
            $('#userRolePaginationDivId').show();

        }
    });
}

function submitForm() {
    var formValidation = validateForm();
    var emailAddress = validateEmailAddress();
    console.log(formValidation);
    if (formValidation) {
        var $form = $('#userRole-form');
        var url = "/userRoles/createUser";
        var request = $.ajax({
            url: url,
            type: 'post',
            data: $form.serialize(),
            success: function (response) {
                $('#userRolesContentId').html(response);
                $('#users-searchview').hide();
                $('#users-createview').show();
                $('#successMsg').show();
                $('#roleId').multiselect();
                $('#networkLoginIdErrMsg').hide();
                $('#institutionIdErrMsg').hide();
                $('#roleIdErrMsg').hide();
                $('#userDescriptionErrMsg').hide();
            }
        });
        $('#networkLoginIdErrMsg').hide();
        $('#institutionIdErrMsg').hide();
        $('#roleIdErrMsg').hide();
        $('#userDescriptionErrMsg').hide();
    }


}
function resetDefaults() {
    $('#networkLoginIdErrMsg').hide();
    $('#institutionIdErrMsg').hide();
    $('#roleIdErrMsg').hide();
    $('#userDescriptionErrMsg').hide();
}

function validateForm() {
    var isValid = true;
    var newNetworkLoginId = $('#networkLoginId').val();
    var institutionId = $('#institutionId').val();
    var roleId = $('#roleId').val();
    var userDescription = $('#userDescription').val();

    if (isBlankValue(newNetworkLoginId)) {
        $('#networkLoginIdErrMsg').show();
        isValid = false;
    } else {
        $('#networkLoginIdErrMsg').hide();
    }
    if (isBlankValue(institutionId)) {
        $('#institutionIdErrMsg').show();
        isValid = false;
    } else {
        $('#institutionIdErrMsg').hide();
    }
    if (isBlankValue(roleId)) {
        $('#roleIdErrMsg').show();
        isValid = false;
    } else {
        $('#roleIdErrMsg').hide();
    }
    if (isBlankValue(userDescription)) {
        $('#userDescriptionErrMsg').show();
        isValid = false;
    } else {
        $('#userDescriptionErrMsg').hide();
    }
    return isValid;
}


function isBlankValue(value) {
    if (value == null || value == '') {
        return true;
    }
    return false;
}

function validateEmailAddress() {
    var isValidEmailAddress = $('#emailId').is(':valid');
    var blankEmailAddress = $('#emailId').val();
    var isValid = true;
    if (blankEmailAddress==null || blankEmailAddress=='') {
        $('#emailIdErrMsg').hide();
        isValid=true;
    } else if(isValidEmailAddress==true){
        $('#emailIdErrMsg').hide();
        isValid=true;
    }
    else if(isValidEmailAddress==false){
        $('#emailIdErrMsg').show();
        isValid=false;
    }
    return isValid;
}


function validateEditEmailAddress() {
    var isValidEmailAddress = $('#editEmailId').is(':valid');
    var blankEmailAddress = $('#editEmailId').val();
    var isValid = true;
    if (blankEmailAddress==null || blankEmailAddress=='') {
        $('#editEmailIdErrMsg').hide();
        isValid=true;
    } else if(isValidEmailAddress==true){
        $('#editEmailIdErrMsg').hide();
        isValid=true;
    }
    else if(isValidEmailAddress==false){
        $('#editEmailIdErrMsg').show();
        isValid=false;
    }
    return isValid;
}

function toggleUserNetworkLoginIdValidation() {
    var networkLoginId = $('#networkLoginId').val();
    if (isBlankValue(networkLoginId)) {
        $('#networkLoginIdErrMsg').show();
    } else {
        $('#networkLoginIdErrMsg').hide();
    }
}

function toggleUserDescriptionValidation() {
    var userDescription = $('#userDescription').val();
    if (isBlankValue(userDescription)) {
        $('#userDescriptionErrMsg').show();
    } else {
        $('#userDescriptionErrMsg').hide();
    }
}

function toggleUserInstitutionValidation() {
    var institutionId = $('#institutionId').val();
    if (isBlankValue(institutionId)) {
        $('#institutionIdErrMsg').show();
    } else {
        $('#institutionIdErrMsg').hide();
    }
}

function toggleUserRolesValidation() {
    var roleId = $('#roleId').val();
    if (isBlankValue(roleId)) {
        $('#roleIdErrMsg').show();
    } else {
        $('#roleIdErrMsg').hide();
    }
}

function toggleEditInstitutionIdValidation() {
    var editinstitutionId = $('#editinstitutionId').val();
    if (isBlankValue(editinstitutionId)) {
        $('#editinstitutionIdErrMsg').show();
    } else {
        $('#editinstitutionIdErrMsg').hide();
    }
}

function toggleEditRoleValidation() {
    var editroleId = $('#editroleId').val();
    if (isBlankValue(editroleId)) {
        $('#editroleIdErrMsg').show();
    } else {
        $('#editroleIdErrMsg').hide();
    }
}

function toggleEditUserDescriptionValidation() {
    var edituserDescription = $('#edituserDescription').val();
    if (isBlankValue(edituserDescription)) {
        $('#edituserDescriptionErrMsg').show();
    } else {
        $('#edituserDescriptionErrMsg').hide();
    }
}
