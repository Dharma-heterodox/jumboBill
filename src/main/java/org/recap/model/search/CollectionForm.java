package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 12/10/16.
 */
public class CollectionForm extends BibliographicMarcForm {

    private String itemBarcodes;
    private boolean showResults = false;
    private boolean selectAll = false;
    private String errorMessage;
    private String barcodesNotFoundErrorMessage;
    private String ignoredBarcodesErrorMessage;
    private List<SearchResultRow> searchResultRows = new ArrayList<>();
    private boolean showModal = false;

    public String getItemBarcodes() {
        return itemBarcodes;
    }

    public void setItemBarcodes(String itemBarcodes) {
        this.itemBarcodes = itemBarcodes;
    }

    public boolean isShowResults() {
        return showResults;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getBarcodesNotFoundErrorMessage() {
        return barcodesNotFoundErrorMessage;
    }

    public void setBarcodesNotFoundErrorMessage(String barcodesNotFoundErrorMessage) {
        this.barcodesNotFoundErrorMessage = barcodesNotFoundErrorMessage;
    }

    public String getIgnoredBarcodesErrorMessage() {
        return ignoredBarcodesErrorMessage;
    }

    public void setIgnoredBarcodesErrorMessage(String ignoredBarcodesErrorMessage) {
        this.ignoredBarcodesErrorMessage = ignoredBarcodesErrorMessage;
    }

    public List<SearchResultRow> getSearchResultRows() {
        if (null == searchResultRows) {
            searchResultRows = new ArrayList<>();
        }
        return searchResultRows;
    }

    public void setSearchResultRows(List<SearchResultRow> searchResultRows) {
        this.searchResultRows = searchResultRows;
    }

    public boolean isShowModal() {
        return showModal;
    }

    public void setShowModal(boolean showModal) {
        this.showModal = showModal;
    }
}
