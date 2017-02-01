package org.recap.model.reports;

import org.recap.model.search.DeaccessionItemResultsRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
public class ReportsResponse {

    private long accessionPrivatePulCount;
    private long accessionPrivateCulCount;
    private long accessionPrivateNyplCount;
    private long accessionSharedPulCount;
    private long accessionSharedCulCount;
    private long accessionSharedNyplCount;
    private long accessionOpenPulCount;
    private long accessionOpenCulCount;
    private long accessionOpenNyplCount;

    private long deaccessionPrivatePulCount;
    private long deaccessionPrivateCulCount;
    private long deaccessionPrivateNyplCount;
    private long deaccessionSharedPulCount;
    private long deaccessionSharedCulCount;
    private long deaccessionSharedNyplCount;
    private long deaccessionOpenPulCount;
    private long deaccessionOpenCulCount;
    private long deaccessionOpenNyplCount;

    private long openPulCgdCount;
    private long openCulCgdCount;
    private long openNyplCgdCount;
    private long sharedPulCgdCount;
    private long sharedCulCgdCount;
    private long sharedNyplCgdCount;
    private long privatePulCgdCount;
    private long privateCulCgdCount;
    private long privateNyplCgdCount;

    private String totalRecordsCount = "0";
    private Integer totalPageCount = 0;
    private String message;
    private List<DeaccessionItemResultsRow> deaccessionItemResultsRows = new ArrayList<>();

    public long getAccessionPrivatePulCount() {
        return accessionPrivatePulCount;
    }

    public void setAccessionPrivatePulCount(long accessionPrivatePulCount) {
        this.accessionPrivatePulCount = accessionPrivatePulCount;
    }

    public long getAccessionPrivateCulCount() {
        return accessionPrivateCulCount;
    }

    public void setAccessionPrivateCulCount(long accessionPrivateCulCount) {
        this.accessionPrivateCulCount = accessionPrivateCulCount;
    }

    public long getAccessionPrivateNyplCount() {
        return accessionPrivateNyplCount;
    }

    public void setAccessionPrivateNyplCount(long accessionPrivateNyplCount) {
        this.accessionPrivateNyplCount = accessionPrivateNyplCount;
    }

    public long getAccessionSharedPulCount() {
        return accessionSharedPulCount;
    }

    public void setAccessionSharedPulCount(long accessionSharedPulCount) {
        this.accessionSharedPulCount = accessionSharedPulCount;
    }

    public long getAccessionSharedCulCount() {
        return accessionSharedCulCount;
    }

    public void setAccessionSharedCulCount(long accessionSharedCulCount) {
        this.accessionSharedCulCount = accessionSharedCulCount;
    }

    public long getAccessionSharedNyplCount() {
        return accessionSharedNyplCount;
    }

    public void setAccessionSharedNyplCount(long accessionSharedNyplCount) {
        this.accessionSharedNyplCount = accessionSharedNyplCount;
    }

    public long getAccessionOpenPulCount() {
        return accessionOpenPulCount;
    }

    public void setAccessionOpenPulCount(long accessionOpenPulCount) {
        this.accessionOpenPulCount = accessionOpenPulCount;
    }

    public long getAccessionOpenCulCount() {
        return accessionOpenCulCount;
    }

    public void setAccessionOpenCulCount(long accessionOpenCulCount) {
        this.accessionOpenCulCount = accessionOpenCulCount;
    }

    public long getAccessionOpenNyplCount() {
        return accessionOpenNyplCount;
    }

    public void setAccessionOpenNyplCount(long accessionOpenNyplCount) {
        this.accessionOpenNyplCount = accessionOpenNyplCount;
    }

    public long getDeaccessionPrivatePulCount() {
        return deaccessionPrivatePulCount;
    }

    public void setDeaccessionPrivatePulCount(long deaccessionPrivatePulCount) {
        this.deaccessionPrivatePulCount = deaccessionPrivatePulCount;
    }

    public long getDeaccessionPrivateCulCount() {
        return deaccessionPrivateCulCount;
    }

    public void setDeaccessionPrivateCulCount(long deaccessionPrivateCulCount) {
        this.deaccessionPrivateCulCount = deaccessionPrivateCulCount;
    }

    public long getDeaccessionPrivateNyplCount() {
        return deaccessionPrivateNyplCount;
    }

    public void setDeaccessionPrivateNyplCount(long deaccessionPrivateNyplCount) {
        this.deaccessionPrivateNyplCount = deaccessionPrivateNyplCount;
    }

    public long getDeaccessionSharedPulCount() {
        return deaccessionSharedPulCount;
    }

    public void setDeaccessionSharedPulCount(long deaccessionSharedPulCount) {
        this.deaccessionSharedPulCount = deaccessionSharedPulCount;
    }

    public long getDeaccessionSharedCulCount() {
        return deaccessionSharedCulCount;
    }

    public void setDeaccessionSharedCulCount(long deaccessionSharedCulCount) {
        this.deaccessionSharedCulCount = deaccessionSharedCulCount;
    }

    public long getDeaccessionSharedNyplCount() {
        return deaccessionSharedNyplCount;
    }

    public void setDeaccessionSharedNyplCount(long deaccessionSharedNyplCount) {
        this.deaccessionSharedNyplCount = deaccessionSharedNyplCount;
    }

    public long getDeaccessionOpenPulCount() {
        return deaccessionOpenPulCount;
    }

    public void setDeaccessionOpenPulCount(long deaccessionOpenPulCount) {
        this.deaccessionOpenPulCount = deaccessionOpenPulCount;
    }

    public long getDeaccessionOpenCulCount() {
        return deaccessionOpenCulCount;
    }

    public void setDeaccessionOpenCulCount(long deaccessionOpenCulCount) {
        this.deaccessionOpenCulCount = deaccessionOpenCulCount;
    }

    public long getDeaccessionOpenNyplCount() {
        return deaccessionOpenNyplCount;
    }

    public void setDeaccessionOpenNyplCount(long deaccessionOpenNyplCount) {
        this.deaccessionOpenNyplCount = deaccessionOpenNyplCount;
    }

    public long getOpenPulCgdCount() {
        return openPulCgdCount;
    }

    public void setOpenPulCgdCount(long openPulCgdCount) {
        this.openPulCgdCount = openPulCgdCount;
    }

    public long getOpenCulCgdCount() {
        return openCulCgdCount;
    }

    public void setOpenCulCgdCount(long openCulCgdCount) {
        this.openCulCgdCount = openCulCgdCount;
    }

    public long getOpenNyplCgdCount() {
        return openNyplCgdCount;
    }

    public void setOpenNyplCgdCount(long openNyplCgdCount) {
        this.openNyplCgdCount = openNyplCgdCount;
    }

    public long getSharedPulCgdCount() {
        return sharedPulCgdCount;
    }

    public void setSharedPulCgdCount(long sharedPulCgdCount) {
        this.sharedPulCgdCount = sharedPulCgdCount;
    }

    public long getSharedCulCgdCount() {
        return sharedCulCgdCount;
    }

    public void setSharedCulCgdCount(long sharedCulCgdCount) {
        this.sharedCulCgdCount = sharedCulCgdCount;
    }

    public long getSharedNyplCgdCount() {
        return sharedNyplCgdCount;
    }

    public void setSharedNyplCgdCount(long sharedNyplCgdCount) {
        this.sharedNyplCgdCount = sharedNyplCgdCount;
    }

    public long getPrivatePulCgdCount() {
        return privatePulCgdCount;
    }

    public void setPrivatePulCgdCount(long privatePulCgdCount) {
        this.privatePulCgdCount = privatePulCgdCount;
    }

    public long getPrivateCulCgdCount() {
        return privateCulCgdCount;
    }

    public void setPrivateCulCgdCount(long privateCulCgdCount) {
        this.privateCulCgdCount = privateCulCgdCount;
    }

    public long getPrivateNyplCgdCount() {
        return privateNyplCgdCount;
    }

    public void setPrivateNyplCgdCount(long privateNyplCgdCount) {
        this.privateNyplCgdCount = privateNyplCgdCount;
    }

    public String getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(String totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DeaccessionItemResultsRow> getDeaccessionItemResultsRows() {
        return deaccessionItemResultsRows;
    }

    public void setDeaccessionItemResultsRows(List<DeaccessionItemResultsRow> deaccessionItemResultsRows) {
        this.deaccessionItemResultsRows = deaccessionItemResultsRows;
    }
}
