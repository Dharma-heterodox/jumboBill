package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/10/16.
 */
public class RequestForm {

    private String patronBarcode;
    private String itemBarcode;
    private String deliveryLocation;
    private String patronBarcodeInRequest;
    private String itemBarcodeInRequest;
    private String deliveryLocationInRequest;
    private String itemTitle;
    private String itemOwningInstitution;
    private String patronEmailAddress;
    private String requestingInstitution;
    private String requestType;
    private String requestNotes;
    private Integer startPage;
    private Integer endPage;
    private Integer volumeNumber;
    private String issue;
    private String articleAuthor;
    private String articleTitle;
    private String message;
    private String errorMessage;
    private String totalRecordsCount = "0";
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Integer totalPageCount = 0;
    private boolean submitted = false;
    private boolean showResults = false;
    private List<String> requestingInstitutions = new ArrayList<>();
    private List<String> requestTypes = new ArrayList<>();
    private List<String> deliveryLocations = new ArrayList<>();
    private List<SearchResultRow> searchResultRows = new ArrayList<>();

    public String getPatronBarcode() {
        return patronBarcode;
    }

    public void setPatronBarcode(String patronBarcode) {
        this.patronBarcode = patronBarcode;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getPatronBarcodeInRequest() {
        return patronBarcodeInRequest;
    }

    public void setPatronBarcodeInRequest(String patronBarcodeInRequest) {
        this.patronBarcodeInRequest = patronBarcodeInRequest;
    }

    public String getItemBarcodeInRequest() {
        return itemBarcodeInRequest;
    }

    public void setItemBarcodeInRequest(String itemBarcodeInRequest) {
        this.itemBarcodeInRequest = itemBarcodeInRequest;
    }

    public String getDeliveryLocationInRequest() {
        return deliveryLocationInRequest;
    }

    public void setDeliveryLocationInRequest(String deliveryLocationInRequest) {
        this.deliveryLocationInRequest = deliveryLocationInRequest;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemOwningInstitution() {
        return itemOwningInstitution;
    }

    public void setItemOwningInstitution(String itemOwningInstitution) {
        this.itemOwningInstitution = itemOwningInstitution;
    }

    public String getPatronEmailAddress() {
        return patronEmailAddress;
    }

    public void setPatronEmailAddress(String patronEmailAddress) {
        this.patronEmailAddress = patronEmailAddress;
    }

    public String getRequestingInstitution() {
        return requestingInstitution;
    }

    public void setRequestingInstitution(String requestingInstitution) {
        this.requestingInstitution = requestingInstitution;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestNotes() {
        return requestNotes;
    }

    public void setRequestNotes(String requestNotes) {
        this.requestNotes = requestNotes;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(Integer volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(String totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isShowResults() {
        return showResults;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public List<String> getRequestingInstitutions() {
        return requestingInstitutions;
    }

    public void setRequestingInstitutions(List<String> requestingInstitutions) {
        this.requestingInstitutions = requestingInstitutions;
    }

    public List<String> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(List<String> requestTypes) {
        this.requestTypes = requestTypes;
    }

    public List<String> getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(List<String> deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public List<SearchResultRow> getSearchResultRows() {
        return searchResultRows;
    }

    public void setSearchResultRows(List<SearchResultRow> searchResultRows) {
        this.searchResultRows = searchResultRows;
    }

    public void resetPageNumber() {
        this.pageNumber = 0;
    }
}
