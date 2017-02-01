/**
 * Created by rajeshbabuk on 13/10/16.
 */

jQuery(document).ready(function ($) {
    showAndHideDefaults();

    $('#collection-result-table').dataTable({
        searching: false,
        sort: false,
        "scrollCollapse": true,
        order: [[1,'asc']],
        scrollY:'287px',
        "orderCellsTop": true,
        "paging":   false,
        "info":     false,
        columnDefs: [
            { targets: [1,2],orderable: false},
            { targets: '_all',orderable: false}
        ]
    });
    
});

/****Collection Tab Modal From Hide/Show*****/
function showAndHideDefaults() {
    if ($('#editCgdclick').is(':checked')) {
        editCgdcontrol();
    } else if ($('#deaccesionclick').is(':checked')) {
        deaccessioncontrol();
    }
}
function editCgdcontrol() {
    $('#editCDG-section').show();
    $('#cgdErrorMessage').hide();
    $('#cgdNotesErrorMessage').hide();
    $('#Deaccession-section').hide();
    $('#locationErrorMessage').hide();
    $('#deaccessionNotesErrorMessage').hide();
}
function deaccessioncontrol() {
    $('#editCDG-section').hide();
    $('#cgdErrorMessage').hide();
    $('#cgdNotesErrorMessage').hide();
    $('#locationErrorMessage').hide();
    $('#deaccessionNotesErrorMessage').hide();
    $('#Deaccession-section').show();
}

function clearBarcodeText() {
    $("#barcodeFieldId").val('');
    $("#clearBarcodeText").hide();
}

function displayRecords() {
    var $form = $('#collection-form');
    var url = $form.attr('action') + "?action=displayRecords";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#collectionContentId').html(response);
        }
    });
}

function openMarcView(bibId, itemId) {
    $("#bibId").val(bibId);
    $("#itemId").val(itemId);

    var $form = $('#collection-form');
    var url = $form.attr('action') + "?action=openMarcView";
    var request = $.ajax({
        url: url,
        type: 'post',
        data: $form.serialize(),
        success: function (response) {
            console.log("completed");
            $('#collectionUpdateModal').html(response);
            $('#collection-result-inner').modal({ show: true });
            showAndHideDefaults();
        }
    });
}

function collectionUpdate() {
    if (isValidInputs()) {
        var $form = $('#collection-form');
        var url = $form.attr('action') + "?action=collectionUpdate";
        var request = $.ajax({
            url: url,
            type: 'post',
            data: $form.serialize(),
            beforeSend: function () {
                $('#collectionModalContent').block({
                    message: '<h1>Processing...</h1>'
                });
            },
            success: function (response) {
                $('#collectionModalContent').unblock();
                console.log("completed");
                var editCgd = $('#editCgdclick').is(':checked');
                var deaccession = $('#deaccesionclick').is(':checked');
                $('#itemDetailsSection').html(response);
                if (editCgd) {
                    $('#Deaccession-section').hide();
                    $('#cgdErrorMessage').hide();
                    $('#cgdNotesErrorMessage').hide();
                    $('#locationErrorMessage').hide();
                    $('#deaccessionNotesErrorMessage').hide();
                } else if (deaccession) {
                    $('#editCDG-section').hide();
                    $('#cgdErrorMessage').hide();
                    $('#locationErrorMessage').hide();
                    $('#deaccessionNotesErrorMessage').hide();
                    $('#cgdNotesErrorMessage').hide();
                }
            }
        });
    }
}

function isValidInputs() {
    var editCgd = $('#editCgdclick').is(':checked');
    var deaccession = $('#deaccesionclick').is(':checked');

    if (editCgd) {
        var oldCgd = $('#cgdField').val();
        var newCgd = $('#newCGD').val();
        var cgdNotes = $('#CGDChangeNotes').val();

        if (isBlankValue(newCgd)) {
            $('#cgdErrorMessage').show();
            $('#cgdNotesErrorMessage').hide();
        } else if (oldCgd == newCgd) {
            $('#cgdErrorMessage').show();
            $('#cgdNotesErrorMessage').hide();
        } else if (oldCgd == 'Shared' && newCgd != 'Shared' && (cgdNotes == null || cgdNotes == '')) {
            $('#cgdNotesErrorMessage').show();
            $('#cgdErrorMessage').hide();
        } else {
            return true;
        }
    } else if (deaccession) {
        var deaccessionType = $('#deaccessionType').val();
        var deliveryLocation = $('#DeliveryLocation').val();
        var deaccessionNotes = $('#DeaccessionNotes').val();

        if (deaccessionType == 'Permanent Withdrawal Direct (PWD)' && (deliveryLocation == null || deliveryLocation == '') && (deaccessionNotes == null || deaccessionNotes == '')) {
            $('#locationErrorMessage').show();
            $('#deaccessionNotesErrorMessage').show();
        } else if (deaccessionType == 'Permanent Withdrawal Direct (PWD)' && (deliveryLocation == null || deliveryLocation == '')) {
            $('#locationErrorMessage').show();
            $('#deaccessionNotesErrorMessage').hide();
        } else if (deaccessionNotes == null || deaccessionNotes == '') {
            $('#deaccessionNotesErrorMessage').show();
            $('#locationErrorMessage').hide();
        } else {
            return true;
        }
    }
    return false;
}

function toggleNewCgdValidation() {
    var oldCgd = $('#cgdField').val();
    var newCgd = $('#newCGD').val();
    if (isBlankValue(newCgd)) {
        $('#cgdErrorMessage').show();
    } else if (oldCgd == newCgd) {
        $('#cgdErrorMessage').show();
    } else {
        $('#cgdErrorMessage').hide();
    }
}

function toggleCgdNotesValidation() {
    var oldCgd = $('#cgdField').val();
    var newCgd = $('#newCGD').val();
    var CGDChangeNotes = $('#CGDChangeNotes').val();
    if (isBlankValue(CGDChangeNotes)) {
        console.log("change");
        $('#cgdNotesErrorMessage').show();
    } else {
        $('#cgdNotesErrorMessage').hide();
    }
}

function toggleDeliveryLocationValidation() {
    var deaccessionType = $('#deaccessionType').val();
    var DeliveryLocation = $('#DeliveryLocation').val();
    if (deaccessionType == 'Permanent Withdrawal Direct (PWD)' && isBlankValue(DeliveryLocation)) {
        $('#locationErrorMessage').show();
    } else {
        $('#locationErrorMessage').hide();
    }
}

function toggleDeaccessionNotesValidation() {
    var DeaccessionNotes = $('#DeaccessionNotes').val();
    if (isBlankValue(DeaccessionNotes)) {
        $('#deaccessionNotesErrorMessage').show();
    } else {
        $('#deaccessionNotesErrorMessage').hide();
    }
}

function isBlankValue(value) {
    if (value == null || value == '') {
        return true;
    }
    return false;
}

