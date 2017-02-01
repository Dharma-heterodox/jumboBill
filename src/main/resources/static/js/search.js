/**
 * Created by rajeshbabuk on 2/8/16.
 */

jQuery(document).ready(function ($) {
    $("#search-result-table").tablesorter();

    if ($("#fieldValue").val().length == 0) {
        $("#clearSearchText").hide();
    }

    $("#fieldValue").keyup(function(e) {
        if ($("#fieldValue").val().length > 0) {
            $("#clearSearchText").show();
        } else {
            $("#clearSearchText").hide();
        }
    });

    toggleFacets();

    $(function(){
        $("#fieldValue").addClear();
    });

});

function clearSearchText() {
    $("#fieldValue").val('');
    $("#clearSearchText").hide();
}

function showItems(resultRowIndex) {
    toggleIcon(resultRowIndex);
    $('#searchResults-' + resultRowIndex).after($("tr.row" + resultRowIndex));
}

function toggleIcon(resultRowIndex) {
    var showItemsInputValue = $('#showItemsInput-' + resultRowIndex).val();
    if (showItemsInputValue == "false") {
        $('#showItemsInput-' + resultRowIndex).val("true");
        $('#showItemsIcon-' + resultRowIndex).removeClass("fa-plus-circle");
        $('#showItemsIcon-' + resultRowIndex).addClass("fa-minus-circle");
    } else {
        $('#showItemsInput-' + resultRowIndex).val("false");
        $('#showItemsIcon-' + resultRowIndex).removeClass("fa-minus-circle");
        $('#showItemsIcon-' + resultRowIndex).addClass("fa-plus-circle");
    }
}

function sortHeader() {
    $("tr.childRow").removeClass('in');
    $('[name=showItemsInput]').val("false");
    $('[name=showItemsIcon]').removeClass("fa-minus-circle");
    $('[name=showItemsIcon]').addClass("fa-plus-circle");
}

function selectAllParentRows() {
    var selectAllFlag = $('#selectAll').is(":checked");
    if (selectAllFlag) {
        $("tr.parentRow #selected").prop('checked', true);
    } else {
        $("tr.parentRow #selected").prop('checked', false);
    }
}

function enableExportSelectedRecordButton(){
    var innerCheckBox = $(".selectInnerCBClass").is(":checked");
    var outerCheckBox = $(".selectOuterCBClass").is(":checked");
    var outerselectAll = $(".outerSelectAllCBClass").is(":checked");
    var innerselectAll = $(".innerSelectAllCBClass").is(":checked");
    if (outerCheckBox || innerCheckBox || outerselectAll || innerselectAll){
        document.getElementById("export").disabled = false;
    }else{
        document.getElementById("export").disabled = true;
    }
}



function selectAllChildRows(childRowIndex) {
    var selectAllFlag = $('#selectAllItems-' + childRowIndex).is(":checked");
    if (selectAllFlag) {
        $("tr.row" + childRowIndex + " #selectedItem").prop('checked', true);
    } else {
        $("tr.row" + childRowIndex + " #selectedItem").prop('checked', false);
    }
}

jQuery(document).keypress(function (e) {
    if (e.which == 13) {
        $("#search").click();
    }
});

function selectOrDeselectFacets() {
    var selectAllFacets = $('#selectAllFacets').is(":checked");
    if(selectAllFacets) {
        $('#owningInstitutionNYPL').prop('checked', true);
        $('#owningInstitutionCUL').prop('checked', true);
        $('#owningInstitutionPUL').prop('checked', true);

        $('#shared').prop('checked', true);
        $('#private').prop('checked', true);
        $('#open').prop('checked', true);

        $('#available').prop('checked', true);
        $('#notAvailable').prop('checked', true);

        $('#monograph').prop('checked', true);
        $('#serials').prop('checked', true);
        $('#others').prop('checked', true);

        $('#noRestriction').prop('checked', true);
        $('#inLibraryUse').prop('checked', true);
        $('#supervisedUse').prop('checked', true);
    } else {
        $('#owningInstitutionNYPL').prop('checked', false);
        $('#owningInstitutionCUL').prop('checked', false);
        $('#owningInstitutionPUL').prop('checked', false);

        $('#shared').prop('checked', false);
        $('#private').prop('checked', false);
        $('#open').prop('checked', false);

        $('#available').prop('checked', false);
        $('#notAvailable').prop('checked', false);

        $('#monograph').prop('checked', false);
        $('#serials').prop('checked', false);
        $('#others').prop('checked', false);

        $('#noRestriction').prop('checked', false);
        $('#inLibraryUse').prop('checked', false);
        $('#supervisedUse').prop('checked', false);
    }
}

function toggleFacets() {
    var showFacets = $('#showFacets').val();
    if (showFacets == "false") {
        $('#showFacets').val("true");
        $('#showFacetsIcon').removeClass("fa-plus-circle");
        $('#showFacetsIcon').addClass("fa-minus-circle");
        $('#showFacetsIcon').css("color", "red");
        $("#searchRecordsFacetTableId").show();
        $("#moreFacetsText").hide();
        $("#hideFacetsText").show();
    } else {
        $('#showFacets').val("false");
        $('#showFacetsIcon').removeClass("fa-minus-circle");
        $('#showFacetsIcon').addClass("fa-plus-circle");
        $('#showFacetsIcon').css("color", "green");
        $("#searchRecordsFacetTableId").hide();
        $("#moreFacetsText").show();
        $("#hideFacetsText").hide();
    }
}

