package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 2/1/17.
 */
public class SearchRecordsResponse {

    private List<SearchResultRow> searchResultRows = new ArrayList<>();
    private Integer totalPageCount = 0;
    private String totalBibRecordsCount = "0";
    private String totalItemRecordsCount = "0";
    private String totalRecordsCount = "0";
    private boolean showTotalCount;
    private String errorMessage;

    public List<SearchResultRow> getSearchResultRows() {
        return searchResultRows;
    }

    public void setSearchResultRows(List<SearchResultRow> searchResultRows) {
        this.searchResultRows = searchResultRows;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getTotalBibRecordsCount() {
        return totalBibRecordsCount;
    }

    public void setTotalBibRecordsCount(String totalBibRecordsCount) {
        this.totalBibRecordsCount = totalBibRecordsCount;
    }

    public String getTotalItemRecordsCount() {
        return totalItemRecordsCount;
    }

    public void setTotalItemRecordsCount(String totalItemRecordsCount) {
        this.totalItemRecordsCount = totalItemRecordsCount;
    }

    public String getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(String totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public boolean isShowTotalCount() {
        return showTotalCount;
    }

    public void setShowTotalCount(boolean showTotalCount) {
        this.showTotalCount = showTotalCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
