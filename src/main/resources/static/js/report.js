/**
 * Created by akulak on 14/12/16.
 */
jQuery(document).ready(function ($) {

    /***Report Tab RequestType Option Show/Hide ***/
    ReportRequestTabOption();
    $('#ReportRequestclick').click(function () {
        $('#RequestDateRangefrom').val('');
        $('#RequestDateRangeto').val('');
        $('#AccessionDeaccessionDateRangefrom').val('');
        $('#AccessionDeaccessionDateRangeto').val('');
        $('#showAccessionDeaccessionTable').hide();
        $('#ILBD-tableview').hide();
        $('#partners-tableview').hide();
        $('#requesttype-tableview').hide();
        $('#showReportResultsText').hide();
        $('#RequestTypeshow').hide();
        $('#ReportShowBy').val('IL_BD');
        $('#recapreports #ReportRequestType').multiselect('deselectAll', false);
        $('#recapreports #ReportRequestType').multiselect('refresh');
        if ($(this).is(':checked')) {
            RequestOption();
        }
    });
    $('#ReportAccessionDeaccessionclick').click(function () {
        $('#RequestDateRangefrom').val('');
        $('#RequestDateRangeto').val('');
        $('#AccessionDeaccessionDateRangefrom').val('');
        $('#AccessionDeaccessionDateRangeto').val('');
        $('#showAccessionDeaccessionTable').hide();
        if ($(this).is(':checked')) {
            AccessionDeaccessionOption();
        }
    });
    $('#ReportCollectionGroupDesignationclick').click(function () {
        $('#RequestDateRangefrom').val('');
        $('#RequestDateRangeto').val('');
        $('#AccessionDeaccessionDateRangefrom').val('');
        $('#AccessionDeaccessionDateRangeto').val('');
        $('#showAccessionDeaccessionTable').hide();
        if ($(this).is(':checked')) {
            CollectionGroupDesignationOption();
        }
    });


    /** Request Type show and hide **/
    var reportShowBy = $('#ReportShowBy').val();
    if (reportShowBy == 'RequestType') {
        $('#RequestTypeshow').show();
        $('#note-ILBD').hide();
        $('#note-partners').hide();
        $('#note-requesttype').show();
    }
    if (reportShowBy == 'Partners') {
        $('#RequestTypeshow').hide();
        $('#note-ILBD').hide();
        $('#note-partners').show();
        $('#note-requesttype').hide();
    }
    if (reportShowBy == 'IL_BD') {
        $('#RequestTypeshow').hide();
        $('#note-ILBD').show();
        $('#note-partners').hide();
        $('#note-requesttype').hide();
    }


    /*** Datepicker ***/
    $('#reportrequesdatepickerfrom,#reportrequesdatepickerto,#reportaccessiondeaccessionsectiondatepickerfrom,#reportaccessiondeaccessionsectiondatepickerto').datepicker({
        format: 'mm/dd/yyyy',
        autoclose: true,
        toggleActive: true,
        todayHighlight: true
    }).on('show', function(e){
        if ( e.date ) {
            $(this).data('stickyDate', e.date);
        } else if($(this).val()){
            /**auto-populate existing selection*/
            $(this).data('stickyDate', new Date($(this).val()));
            $(this).datepicker('setDate', new Date($(this).val()));
        } else {
            $(this).data('stickyDate', null);
        }
    }).on('hide', function(e){
        var stickyDate = $(this).data('stickyDate');
        if ( !e.date && stickyDate ) {
            $(this).datepicker('setDate', stickyDate);
            $(this).data('stickyDate', null);
        }
    });

    /*** Multiselect ***/
    $('#recapreports #ReportRequestType').multiselect();

    /****Report Tab RequestType Option Show/Hide*****/
    function ReportRequestTabOption() {
        $('#ReportRequestclickview').hide();
        if ($('#recapreports #ReportRequestclick').is(':checked')) {
            RequestOption();
        } else if ($('#recapreports #ReportAccessionDeaccessionclick').is(':checked')) {
            AccessionDeaccessionOption();
        } else if ($('#recapreports #ReportCollectionGroupDesignationclick').is(':checked')) {
            CollectionGroupDesignationOption();
        }
    }

    function RequestOption() {
        $('#recapreports #ReportRequestclickview').show();
        $('#recapreports #ReportAccessionDeaccessionclickview').hide();
        $('#recapreports #ReportCollectionGroupDesignationclickview').hide();
        $('#requestFromDateErrorText').hide();
        $('#requestToDateErrorText').hide();
        $('#accessionErrorText').hide();
        $('#requestTypeErrorText').hide();

    }

    function AccessionDeaccessionOption() {
        $('#recapreports #ReportRequestclickview').hide();
        $('#recapreports #ReportAccessionDeaccessionclickview').show();
        $('#recapreports #ReportCollectionGroupDesignationclickview').hide();
        $('#requestFromDateErrorText').hide();
        $('#requestToDateErrorText').hide();
        $('#accessionErrorText').hide();

    }

    function CollectionGroupDesignationOption() {
        $('#recapreports #ReportRequestclickview').hide();
        $('#recapreports #ReportAccessionDeaccessionclickview').hide();
        $('#recapreports #ReportCollectionGroupDesignationclickview').show();
        $('#requestFromDateErrorText').hide();
        $('#requestToDateErrorText').hide();
        $('#accessionErrorText').hide();
    }


    /***Report Tab ShowBy Select Section Show/Hide ***/
    $(function () {
        $('#ReportShowBy').change(function () {
            $('#' + $(this).val()).show();
            if ($(this).find(':selected').val() === 'IL_BD') {
                $('#showReportResultsText').hide();
                $('#RequestTypeshow').hide();
                $('#requesttype-tableview').hide();
                $('#partners-tableview').hide();
                $('#ILBD-tableview').hide();
                $('#note-ILBD').show();
                $('#note-partners').hide();
                $('#note-requesttype').hide();
                $('#showByErrorText').hide();
                $('#requestFromDateErrorText').hide();
                $('#requestToDateErrorText').hide();
                $('#RequestDateRangefrom').val('');
                $('#RequestDateRangeto').val('');
            } else if ($(this).find(':selected').val() === 'Partners') {
                $('#showReportResultsText').hide();
                $('#RequestTypeshow').hide();
                $('#requesttype-tableview').hide();
                $('#partners-tableview').show();
                $('#ILBD-tableview').hide();
                $('#note-ILBD').hide();
                $('#note-partners').show();
                $('#note-requesttype').hide();
                $('#showByErrorText').hide();
                $('#requestFromDateErrorText').hide();
                $('#requestToDateErrorText').hide();
                $('#RequestDateRangefrom').val('');
                $('#RequestDateRangeto').val('');
            } else if ($(this).find(':selected').val() === 'RequestType') {
                $('#showReportResultsText').hide();
                $('#RequestTypeshow').show();
                $('#requesttype-tableview').hide();
                $('#partners-tableview').hide();
                $('#ILBD-tableview').hide();
                $('#note-ILBD').hide();
                $('#note-partners').hide();
                $('#note-requesttype').show();
                $('#showByErrorText').hide();
                $('#requestFromDateErrorText').hide();
                $('#requestToDateErrorText').hide();
                $('#RequestDateRangefrom').val('');
                $('#RequestDateRangeto').val('');
                $('#AccessionDeaccessionDateRangefrom').val('');
                $('#AccessionDeaccessionDateRangeto').val('');
                $('#recapreports #ReportRequestType').multiselect('deselectAll', false);
                $('#recapreports #ReportRequestType').multiselect('refresh');
            }
        });


    });

    $('#searchButton').click(function () {
        var showBy = $('#ReportShowBy').val();
        var requestFromDate = $('#RequestDateRangefrom').val();
        var requestToDate = $('#RequestDateRangeto').val();
        var reportRequestType = $('#ReportRequestType').val();
        if (isBlankValue(showBy)) {
            $('#showByErrorText').show(event.preventDefault());
        }
        if (isBlankValue(requestFromDate)) {
            $('#requestFromDateErrorText').show(event.preventDefault());
        }
        if (isBlankValue(requestToDate)) {
            $('#requestToDateErrorText').show(event.preventDefault());
        }
        if (!isBlankValue(requestFromDate) && isBlankValue(requestToDate)) {
            $('#requestFromDateErrorText').hide();
            $('#requestToDateErrorText').show(event.preventDefault());

        }
        if (isBlankValue(requestFromDate) && (!isBlankValue(requestToDate))) {
            $('#requestFromDateErrorText').show(event.preventDefault());
            $('#requestToDateErrorText').hide();

        }
        if ((showBy == 'RequestType') && isBlankValue(reportRequestType)) {
            $('#requestTypeErrorText').show(event.preventDefault());
        }


    });

    $('#accessionSearchButton').click(function () {
        var fromDate = $('#AccessionDeaccessionDateRangefrom').val();
        var toDate = $('#AccessionDeaccessionDateRangeto').val();

        if (isBlankValue(fromDate)) {
            $('#accessionErrorText').show(event.preventDefault());
        }
        if (isBlankValue(toDate)) {
            $('#deaccessionErrorText').show(event.preventDefault());
        }
        if ((!isBlankValue(fromDate)) && isBlankValue(toDate)) {
            $('#accessionErrorText').hide();
            $('#deaccessionErrorText').show(event.preventDefault());
        }
        if (isBlankValue(fromDate) && (!isBlankValue(toDate))) {
            $('#accessionErrorText').show(event.preventDefault());
            $('#deaccessionErrorText').hide();
        }

    });

    function isBlankValue(value) {
        if (value == null || value == '') {
            return true;
        }
        return false;
    }

    $('#RequestDateRangefrom').click(function () {
        $('#requestFromDateErrorText').hide();
    });

    $('#ReportRequestType').click(function () {
      $('#requestTypeErrorText').hide();
    });

    $('#RequestDateRangeto').click(function () {
        $('#requestToDateErrorText').hide();
    });

    $('#AccessionDeaccessionDateRangefrom').click(function () {
        $('#accessionErrorText').hide();
    });

    $('#AccessionDeaccessionDateRangeto').click(function () {
        $('#deaccessionErrorText').hide();
    });


    /***Reports Tab Deaccession Table Show and Reports Main Section Hide ***/
    $("#ReportAccessionDeaccessionclickview a.Deaccession-tableshow").click(function (e) {
        $(".reports-main-section").hide();
        $("#Deaccession-tableview").show();
        e.preventDefault();
    });

    $("#Deaccession-tableview .Deaccessionbacklink a").click(function (e) {
        $(".reports-main-section").show();
        $("#Deaccession-tableview").hide();
        e.preventDefault();
    });


    $('#numOfRecordsId').change(function (){
        var $form = $('#reports-form');
        var url = "/reports/deaccessionInformation";
        $.ajax({
            url: url,
            type: 'get',
            data :$form.serialize(),
            success: function (response) {
                $('#deaccessionInformation').html(response);
            }
        });

    });


});

function cgd() {
    var url = "/reports/collectionGroupDesignation";
    $.ajax({
        url: url,
        type: 'get',
        success: function (response) {
            console.log("completed");
            $('#cgdTable').html(response);
        }
    });
}

function deaccessionPul() {
    $('#reportsPageNumber').val(0);
    $('#reportstotalPageCount').val(0);
    $('#numOfRecordsId').val('10');
    var $form = $('#reports-form');
    $('#ownInst').val('PUL');
    var url = "/reports/deaccessionInformation";
    $.ajax({
        url: url,
        type: 'get',
        data :$form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });

}


function deaccessionCul() {
    $('#reportsPageNumber').val(0);
    $('#reportstotalPageCount').val(0);
    $('#numOfRecordsId').val('10');
    var $form = $('#reports-form');
    $('#ownInst').val('CUL');
    var url = "/reports/deaccessionInformation";
    $.ajax({
        url: url,
        type: 'get',
        data :$form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });

}

function deaccessionNypl() {
    $('#reportsPageNumber').val(0);
    $('#reportstotalPageCount').val(0);
    $('#numOfRecordsId').val('10');
    var $form = $('#reports-form');
    $('#ownInst').val('NYPL');
    var url = "/reports/deaccessionInformation";
    $.ajax({
        url: url,
        type: 'get',
        data :$form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });

}


function first() {
    var $form = $('#reports-form');
    var url = "/reports/first";
    $.ajax({
        url: url,
        type: 'get',
        data: $form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });
}

function previous() {
    var $form = $('#reports-form');
    var url = "/reports/previous";
    $.ajax({
        url: url,
        type: 'get',
        data: $form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });
}

function next() {
    var $form = $('#reports-form');
    var url = "/reports/next";
    $.ajax({
        url: url,
        type: 'get',
        data: $form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });
}

function last() {
    var $form = $('#reports-form');
    var url = "/reports/last";
    $.ajax({
        url: url,
        type: 'get',
        data: $form.serialize(),
        success: function (response) {
            $('#deaccessionInformation').html(response);
        }
    });
}
