package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/10/16.
 */
public class ReportsForm {

    private String showBy;
    private String requestType;
    private String requestFromDate;
    private String requestToDate;
    private String accessionDeaccessionFromDate;
    private String accessionDeaccessionToDate;

    private long ilRequestPulCount;
    private long ilRequestCulCount;
    private long ilRequestNyplCount;
    private long bdRequestPulCount;
    private long bdRequestCulCount;
    private long bdRequestNyplCount;

    private long retrievalRequestPulCount;
    private long retrievalRequestCulCount;
    private long retrievalRequestNyplCount;


    private long recallRequestPulCount;
    private long recallRequestCulCount;
    private long recallRequestNyplCount;

    private long physicalPrivatePulCount;
    private long physicalPrivateCulCount;
    private long physicalPrivateNyplCount;

    private long physicalSharedPulCount;
    private long physicalSharedCulCount;
    private long physicalSharedNyplCount;

    private long eddPrivatePulCount;
    private long eddPrivateCulCount;
    private long eddPrivateNyplCount;

    private long eddSharedOpenPulCount;
    private long eddSharedOpenCulCount;
    private long eddSharedOpenNyplCount;

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

    private boolean showILBDResults=false;
    private boolean showPartners=false;
    private boolean showRequestTypeTable=false;
    private boolean showAccessionDeaccessionTable=false;
    private boolean showReportResultsText=false;
    private boolean showNoteILBD=false;
    private boolean showNotePartners=false;
    private boolean showNoteRequestType=false;

    private boolean showRetrievalTable=false;
    private boolean showRecallTable=false;
    private boolean showRequestTypeShow=false;

    private List<String> reportRequestType=new ArrayList<>();
    private List<String> owningInstitutions = new ArrayList<>();
    private List<String> collectionGroupDesignations = new ArrayList<>();
    private List<DeaccessionItemResultsRow> deaccessionItemResultsRows = new ArrayList<>();

    private boolean showDeaccessionInformationTable = false;

    private String totalRecordsCount = "0";
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Integer totalPageCount = 0;
    private String deaccessionOwnInst;


    public ReportsForm() {
        this.getOwningInstitutions().add("NYPL");
        this.getOwningInstitutions().add("CUL");
        this.getOwningInstitutions().add("PUL");

        this.getCollectionGroupDesignations().add("Shared");
        this.getCollectionGroupDesignations().add("Private");
        this.getCollectionGroupDesignations().add("Open");
    }

    public String getShowBy() {
        return showBy;
    }

    public void setShowBy(String showBy) {
        this.showBy = showBy;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestFromDate() {
        return requestFromDate;
    }

    public void setRequestFromDate(String requestFromDate) {
        this.requestFromDate = requestFromDate;
    }

    public String getRequestToDate() {
        return requestToDate;
    }

    public void setRequestToDate(String requestToDate) {
        this.requestToDate = requestToDate;
    }

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

    public long getIlRequestPulCount() {
        return ilRequestPulCount;
    }

    public void setIlRequestPulCount(long ilRequestPulCount) {
        this.ilRequestPulCount = ilRequestPulCount;
    }

    public long getIlRequestCulCount() {
        return ilRequestCulCount;
    }

    public void setIlRequestCulCount(long ilRequestCulCount) {
        this.ilRequestCulCount = ilRequestCulCount;
    }

    public long getBdRequestPulCount() {
        return bdRequestPulCount;
    }

    public void setBdRequestPulCount(long bdRequestPulCount) {
        this.bdRequestPulCount = bdRequestPulCount;
    }

    public long getIlRequestNyplCount() {
        return ilRequestNyplCount;
    }

    public void setIlRequestNyplCount(long ilRequestNyplCount) {
        this.ilRequestNyplCount = ilRequestNyplCount;
    }

    public long getBdRequestCulCount() {
        return bdRequestCulCount;
    }

    public void setBdRequestCulCount(long bdRequestCulCount) {
        this.bdRequestCulCount = bdRequestCulCount;
    }

    public long getBdRequestNyplCount() {
        return bdRequestNyplCount;
    }

    public void setBdRequestNyplCount(long bdRequestNyplCount) {
        this.bdRequestNyplCount = bdRequestNyplCount;
    }

    public long getRetrievalRequestPulCount() {
        return retrievalRequestPulCount;
    }

    public void setRetrievalRequestPulCount(long  retrievalRequestPulCount) {
        this.retrievalRequestPulCount = retrievalRequestPulCount;
    }

    public long  getRetrievalRequestCulCount() {
        return retrievalRequestCulCount;
    }

    public void setRetrievalRequestCulCount(long  retrievalRequestCulCount) {
        this.retrievalRequestCulCount = retrievalRequestCulCount;
    }

    public long  getRetrievalRequestNyplCount() {
        return retrievalRequestNyplCount;
    }

    public void setRetrievalRequestNyplCount(long  retrievalRequestNyplCount) {
        this.retrievalRequestNyplCount = retrievalRequestNyplCount;
    }

    public long getRecallRequestPulCount() {
        return recallRequestPulCount;
    }

    public void setRecallRequestPulCount(long recallRequestPulCount) {
        this.recallRequestPulCount = recallRequestPulCount;
    }

    public long getRecallRequestCulCount() {
        return recallRequestCulCount;
    }

    public void setRecallRequestCulCount(long recallRequestCulCount) {
        this.recallRequestCulCount = recallRequestCulCount;
    }

    public long getRecallRequestNyplCount() {
        return recallRequestNyplCount;
    }

    public void setRecallRequestNyplCount(long recallRequestNyplCount) {
        this.recallRequestNyplCount = recallRequestNyplCount;
    }

    public long getPhysicalPrivatePulCount() {
        return physicalPrivatePulCount;
    }

    public void setPhysicalPrivatePulCount(long physicalPrivatePulCount) {
        this.physicalPrivatePulCount = physicalPrivatePulCount;
    }

    public long getPhysicalPrivateCulCount() {
        return physicalPrivateCulCount;
    }

    public void setPhysicalPrivateCulCount(long physicalPrivateCulCount) {
        this.physicalPrivateCulCount = physicalPrivateCulCount;
    }

    public long getPhysicalPrivateNyplCount() {
        return physicalPrivateNyplCount;
    }

    public void setPhysicalPrivateNyplCount(long physicalPrivateNyplCount) {
        this.physicalPrivateNyplCount = physicalPrivateNyplCount;
    }

    public long getPhysicalSharedPulCount() {
        return physicalSharedPulCount;
    }

    public void setPhysicalSharedPulCount(long physicalSharedPulCount) {
        this.physicalSharedPulCount = physicalSharedPulCount;
    }

    public long getPhysicalSharedCulCount() {
        return physicalSharedCulCount;
    }

    public void setPhysicalSharedCulCount(long physicalSharedCulCount) {
        this.physicalSharedCulCount = physicalSharedCulCount;
    }

    public long getPhysicalSharedNyplCount() {
        return physicalSharedNyplCount;
    }

    public void setPhysicalSharedNyplCount(long physicalSharedNyplCount) {
        this.physicalSharedNyplCount = physicalSharedNyplCount;
    }

    public boolean isShowILBDResults() {
        return showILBDResults;
    }

    public void setShowILBDResults(boolean showILBDResults) {
        this.showILBDResults = showILBDResults;
    }

    public boolean isShowPartners() {
        return showPartners;
    }

    public void setShowPartners(boolean showPartners) {
        this.showPartners = showPartners;
    }

    public long getEddPrivatePulCount() {
        return eddPrivatePulCount;
    }

    public void setEddPrivatePulCount(long eddPrivatePulCount) {
        this.eddPrivatePulCount = eddPrivatePulCount;
    }

    public long getEddPrivateCulCount() {
        return eddPrivateCulCount;
    }

    public void setEddPrivateCulCount(long eddPrivateCulCount) {
        this.eddPrivateCulCount = eddPrivateCulCount;
    }

    public long getEddPrivateNyplCount() {
        return eddPrivateNyplCount;
    }

    public void setEddPrivateNyplCount(long eddPrivateNyplCount) {
        this.eddPrivateNyplCount = eddPrivateNyplCount;
    }

    public long getEddSharedOpenPulCount() {
        return eddSharedOpenPulCount;
    }

    public void setEddSharedOpenPulCount(long eddSharedOpenPulCount) {
        this.eddSharedOpenPulCount = eddSharedOpenPulCount;
    }

    public long getEddSharedOpenCulCount() {
        return eddSharedOpenCulCount;
    }

    public void setEddSharedOpenCulCount(long eddSharedOpenCulCount) {
        this.eddSharedOpenCulCount = eddSharedOpenCulCount;
    }

    public long getEddSharedOpenNyplCount() {
        return eddSharedOpenNyplCount;
    }

    public void setEddSharedOpenNyplCount(long eddSharedOpenNyplCount) {
        this.eddSharedOpenNyplCount = eddSharedOpenNyplCount;
    }

    public boolean isShowAccessionDeaccessionTable() {
        return showAccessionDeaccessionTable;
    }

    public void setShowAccessionDeaccessionTable(boolean showAccessionDeaccessionTable) {
        this.showAccessionDeaccessionTable = showAccessionDeaccessionTable;
    }

    public boolean isShowReportResultsText() {
        return showReportResultsText;
    }

    public void setShowReportResultsText(boolean showReportResultsText) {
        this.showReportResultsText = showReportResultsText;
    }

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

    public boolean isShowRequestTypeTable() {
        return showRequestTypeTable;
    }

    public void setShowRequestTypeTable(boolean showRequestTypeTable) {
        this.showRequestTypeTable = showRequestTypeTable;
    }

    public boolean isShowNoteILBD() {
        return showNoteILBD;
    }

    public void setShowNoteILBD(boolean showNoteILBD) {
        this.showNoteILBD = showNoteILBD;
    }

    public boolean isShowNotePartners() {
        return showNotePartners;
    }

    public void setShowNotePartners(boolean showNotePartners) {
        this.showNotePartners = showNotePartners;
    }

    public boolean isShowNoteRequestType() {
        return showNoteRequestType;
    }

    public void setShowNoteRequestType(boolean showNoteRequestType) {
        this.showNoteRequestType = showNoteRequestType;
    }

    public List<String> getReportRequestType() {
        return reportRequestType;
    }

    public void setReportRequestType(List<String> reportRequestType) {
        this.reportRequestType = reportRequestType;
    }

    public boolean isShowRetrievalTable() {
        return showRetrievalTable;
    }

    public void setShowRetrievalTable(boolean showRetrievalTable) {
        this.showRetrievalTable = showRetrievalTable;
    }

    public boolean isShowRecallTable() {
        return showRecallTable;
    }

    public void setShowRecallTable(boolean showRecallTable) {
        this.showRecallTable = showRecallTable;
    }

    public List<String> getOwningInstitutions() {
        if (null == owningInstitutions) {
            owningInstitutions = new ArrayList<>();
        }
        return owningInstitutions;
    }

    public void setOwningInstitutions(List<String> owningInstitutions) {
        this.owningInstitutions = owningInstitutions;
    }

    public List<String> getCollectionGroupDesignations() {
        if (null == collectionGroupDesignations) {
            collectionGroupDesignations = new ArrayList<>();
        }
        return collectionGroupDesignations;
    }

    public void setCollectionGroupDesignations(List<String> collectionGroupDesignations) {
        this.collectionGroupDesignations = collectionGroupDesignations;
    }

    public boolean isShowRequestTypeShow() {
        return showRequestTypeShow;
    }

    public void setShowRequestTypeShow(boolean showRequestTypeShow) {
        this.showRequestTypeShow = showRequestTypeShow;
    }

    public List<DeaccessionItemResultsRow> getDeaccessionItemResultsRows() {
        return deaccessionItemResultsRows;
    }

    public void setDeaccessionItemResultsRows(List<DeaccessionItemResultsRow> deaccessionItemResultsRows) {
        this.deaccessionItemResultsRows = deaccessionItemResultsRows;
    }

    public boolean isShowDeaccessionInformationTable() {
        return showDeaccessionInformationTable;
    }

    public void setShowDeaccessionInformationTable(boolean showDeaccessionInformationTable) {
        this.showDeaccessionInformationTable = showDeaccessionInformationTable;
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

    public String getDeaccessionOwnInst() {
        return deaccessionOwnInst;
    }

    public void setDeaccessionOwnInst(String deaccessionOwnInst) {
        this.deaccessionOwnInst = deaccessionOwnInst;
    }
}
