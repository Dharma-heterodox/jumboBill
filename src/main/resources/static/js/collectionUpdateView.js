/**
 * Created by rajeshbabuk on 20/10/16.
 */

jQuery(document).ready(function ($) {
    
    showAndHideDefaults();
    
    $('#editCgdAction').click(function () {
        if ($(this).is(':checked')) {
            editCgdDefaults();
        }
    });
    
    $('#deaccesionAction').click(function () {
        if ($(this).is(':checked')) {
            deaccessionDefaults();
        }
    });
    
});

function showAndHideDefaults() {
    if ($('#editCgdAction').is(':checked')) {
        editCgdDefaults();
    } else if ($('#deaccesionAction').is(':checked')) {
        deaccessionDefaults();
    }

    var submitted = $('#submitted').val();
    if (submitted == 'true') {
        $('#collectionActionSelection').hide();
    }
}

function editCgdDefaults() {
    $('#updateCGDNewRow').show();
    $('#updateCGDNotesRow').show();
    $('#deaccessionTypeRow').hide();
    $('#deaccessionLocationRow').hide();
    $('#deaccessionNotesRow').hide();

    $('#cgdErrorMessage').hide();
    $('#cgdNotesErrorMessage').hide();
    $('#locationErrorMessage').hide();
    $('#deaccessionNotesErrorMessage').hide();
}

function deaccessionDefaults() {
    $('#deaccessionTypeRow').show();
    $('#deaccessionLocationRow').show();
    $('#deaccessionNotesRow').show();
    $('#updateCGDNewRow').hide();
    $('#updateCGDNotesRow').hide();

    $('#cgdErrorMessage').hide();
    $('#cgdNotesErrorMessage').hide();
    $('#locationErrorMessage').hide();
    $('#deaccessionNotesErrorMessage').hide();
}

function validateInputs() {
    var editCgd = $('#editCgdAction').is(':checked');
    var deaccession = $('#deaccesionAction').is(':checked');

    if (editCgd) {
        var oldCgd = $('#cgdField').val();
        var newCgd = $('#newCgdField').val();
        var cgdNotes = $('#cgdChangeNotesField').val();

        if (oldCgd == newCgd) {
            $('#cgdErrorMessage').show();
            $('#cgdNotesErrorMessage').hide();
        } else if (oldCgd == 'Shared' && newCgd != 'Shared' && (cgdNotes == null || cgdNotes == '')) {
            $('#cgdNotesErrorMessage').show();
            $('#cgdErrorMessage').hide();
        } else {
            $('#hiddenCollectionUpdateButton').click();
        }
    } else if (deaccession) {
        var deaccessionType = $('#deaccessionType').val();
        var deliveryLocation = $('#deliveryLocation').val();
        var deaccessionNotes = $('#deaccessionNotesField').val();

        if (deaccessionType == 'Permanent Withdrawl Direct (PWD)' && (deliveryLocation == null || deliveryLocation == '') && (deaccessionNotes == null || deaccessionNotes == '')) {
            $('#locationErrorMessage').show();
            $('#deaccessionNotesErrorMessage').show();
        } else if (deaccessionType == 'Permanent Withdrawl Direct (PWD)' && (deliveryLocation == null || deliveryLocation == '')) {
            $('#locationErrorMessage').show();
            $('#deaccessionNotesErrorMessage').hide();
        } else if (deaccessionNotes == null || deaccessionNotes == '') {
            $('#deaccessionNotesErrorMessage').show();
            $('#locationErrorMessage').hide();
        } else {
            $('#hiddenCollectionUpdateButton').click();
        }

    }
}