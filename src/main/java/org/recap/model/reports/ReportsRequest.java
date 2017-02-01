package org.recap.model.reports;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
public class ReportsRequest {

    private String accessionDeaccessionFromDate;
    private String accessionDeaccessionToDate;
    private List<String> owningInstitutions = new ArrayList<>();
    private List<String> collectionGroupDesignations = new ArrayList<>();
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private String deaccessionOwningInstitution;

    public String getAccessionDeaccessionFromDate() {
        return accessionDeaccessionFromDate;
    }

    public void setAccessionDeaccessionFromDate(String accessionDeaccessionFromDate) {
        this.accessionDeaccessionFromDate = accessionDeaccessionFromDate;
    }

    public String getAccessionDeaccessionToDate() {
        return accessionDeaccessionToDate;
    }

    public void setAccessionDeaccessionToDate(String accessionDeaccessionToDate) {
        this.accessionDeaccessionToDate = accessionDeaccessionToDate;
    }

    public List<String> getOwningInstitutions() {
        return owningInstitutions;
    }

    public void setOwningInstitutions(List<String> owningInstitutions) {
        this.owningInstitutions = owningInstitutions;
    }

    public List<String> getCollectionGroupDesignations() {
        return collectionGroupDesignations;
    }

    public void setCollectionGroupDesignations(List<String> collectionGroupDesignations) {
        this.collectionGroupDesignations = collectionGroupDesignations;
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

    public String getDeaccessionOwningInstitution() {
        return deaccessionOwningInstitution;
    }

    public void setDeaccessionOwningInstitution(String deaccessionOwningInstitution) {
        this.deaccessionOwningInstitution = deaccessionOwningInstitution;
    }
}
