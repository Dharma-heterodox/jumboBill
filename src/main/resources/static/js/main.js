$(document).ready(function () {
    
	/*** Data Table Events ****/
	$('#search-content-tab li a[data-toggle="tab"]').on( 'shown.bs.tab', function (e) {
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    });
    
    var docheight = $(window).height();
    var headerheight = $('header .container-fluid').height();
    var footerheight = $('footer .container-fluid').height();
    var containerheight = (docheight - (headerheight + footerheight));
    $('section .container-fluid').css("min-height",(containerheight)+"px");

});