package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by rajeshbabuk on 22/7/16.
 */
public class BibliographicMarcForm {

    private Integer bibId;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String owningInstitution;
    private String callNumber;
    private String leaderMaterialType;
    private String tag000;
    private String controlNumber001;
    private String controlNumber005;
    private String controlNumber008;
    private String content;
    private List<BibDataField> bibDataFields = new ArrayList<>();
    private String errorMessage;

    private Integer itemId;
    private String availability;
    private String barcode;
    private String locationCode;
    private String useRestriction;
    private String monographCollectionGroupDesignation;
    private String collectionGroupDesignation;
    private String newCollectionGroupDesignation;
    private String cgdChangeNotes;
    private String customerCode;
    private String deaccessionType;
    private String deaccessionNotes;
    private List<String> deliveryLocations = new ArrayList<>();
    private String deliveryLocation;
    private boolean shared = false;
    private boolean submitted = false;
    private String message;
    private String collectionAction;
    private boolean allowEdit;

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }

    public Integer getBibId() {
        return bibId;
    }

    public void setBibId(Integer bibId) {
        this.bibId = bibId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getOwningInstitution() {
        return owningInstitution;
    }

    public void setOwningInstitution(String owningInstitution) {
        this.owningInstitution = owningInstitution;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getTag000() {
        return tag000;
    }

    public void setTag000(String tag000) {
        this.tag000 = tag000;
    }

    public String getControlNumber001() {
        return controlNumber001;
    }

    public void setControlNumber001(String controlNumber001) {
        this.controlNumber001 = controlNumber001;
    }

    public String getControlNumber005() {
        return controlNumber005;
    }

    public void setControlNumber005(String controlNumber005) {
        this.controlNumber005 = controlNumber005;
    }

    public String getControlNumber008() {
        return controlNumber008;
    }

    public void setControlNumber008(String controlNumber008) {
        this.controlNumber008 = controlNumber008;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<BibDataField> getBibDataFields() {
        return bibDataFields;
    }

    public void setBibDataFields(List<BibDataField> bibDataFields) {
        this.bibDataFields = bibDataFields;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }



    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getUseRestriction() {
        return useRestriction;
    }

    public void setUseRestriction(String useRestriction) {
        this.useRestriction = useRestriction;
    }

    public String getCollectionGroupDesignation() {
        return collectionGroupDesignation;
    }

    public void setCollectionGroupDesignation(String collectionGroupDesignation) {
        this.collectionGroupDesignation = collectionGroupDesignation;
    }

    public String getNewCollectionGroupDesignation() {
        return newCollectionGroupDesignation;
    }

    public void setNewCollectionGroupDesignation(String newCollectionGroupDesignation) {
        this.newCollectionGroupDesignation = newCollectionGroupDesignation;
    }

    public String getCgdChangeNotes() {
        return cgdChangeNotes;
    }

    public void setCgdChangeNotes(String cgdChangeNotes) {
        this.cgdChangeNotes = cgdChangeNotes;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDeaccessionType() {
        return deaccessionType;
    }

    public void setDeaccessionType(String deaccessionType) {
        this.deaccessionType = deaccessionType;
    }

    public String getDeaccessionNotes() {
        return deaccessionNotes;
    }

    public void setDeaccessionNotes(String deaccessionNotes) {
        this.deaccessionNotes = deaccessionNotes;
    }

    public List<String> getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(List<String> deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCollectionAction() {
        return collectionAction;
    }

    public void setCollectionAction(String collectionAction) {
        this.collectionAction = collectionAction;
    }

    public String getMonographCollectionGroupDesignation() {
        return monographCollectionGroupDesignation;
    }

    public void setMonographCollectionGroupDesignation(String monographCollectionGroupDesignation) {
        this.monographCollectionGroupDesignation = monographCollectionGroupDesignation;
    }

    public String getLeaderMaterialType() {
        return leaderMaterialType;
    }

    public void setLeaderMaterialType(String leaderMaterialType) {
        this.leaderMaterialType = leaderMaterialType;
    }
}
