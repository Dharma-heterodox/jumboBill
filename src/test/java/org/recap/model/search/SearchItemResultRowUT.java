package org.recap.model.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by premkb on 2/8/16.
 */
public class SearchItemResultRowUT {

    @Test
    public void testSearchItemResultRow(){
        SearchItemResultRow searchItemResultRow = new SearchItemResultRow();
        setSearchItemResultRow(searchItemResultRow);
        assertNotNull(searchItemResultRow);
        assertEquals("Available",searchItemResultRow.getAvailability());
        assertEquals("BC123",searchItemResultRow.getBarcode());
        assertEquals("CL425",searchItemResultRow.getCallNumber());
        assertEquals("CE",searchItemResultRow.getChronologyAndEnum());
        assertEquals("CG",searchItemResultRow.getCollectionGroupDesignation());
        assertEquals("NA",searchItemResultRow.getCustomerCode());
        assertEquals("Allowed",searchItemResultRow.getUseRestriction());
    }

    private void setSearchItemResultRow(SearchItemResultRow searchItemResultRow){
        searchItemResultRow.setAvailability("Available");
        searchItemResultRow.setBarcode("BC123");
        searchItemResultRow.setCallNumber("CL425");
        searchItemResultRow.setChronologyAndEnum("CE");
        searchItemResultRow.setCollectionGroupDesignation("CG");
        searchItemResultRow.setCustomerCode("NA");
        searchItemResultRow.setUseRestriction("Allowed");
    }

    @Test
    public void testSortSearchItemResultRowsOnChronAndEnum() {
        List<SearchItemResultRow> searchItemResultRows = new ArrayList<>();
        SearchItemResultRow searchItemResultRow1 = buildSearchItemResultRow("Call Number1", "v. 111-112", "CC 1", "1", "Library Use", "Shared", "Available");
        SearchItemResultRow searchItemResultRow2 = buildSearchItemResultRow("Call Number2", "v. 116-117", "CC 2", "2", "Library Use", "Shared", "Available");
        SearchItemResultRow searchItemResultRow3 = buildSearchItemResultRow("Call Number3", "v. 100", "CC 3", "3", "Library Use", "Shared", "Available");
        SearchItemResultRow searchItemResultRow4 = buildSearchItemResultRow("Call Number4", "v. 113-114", "CC 4", "4", "Library Use", "Shared", "Available");
        SearchItemResultRow searchItemResultRow5 = buildSearchItemResultRow("Call Number5", "v. 120", "CC 4", "5", "Library Use", "Shared", "Available");
        searchItemResultRows.add(searchItemResultRow1);
        searchItemResultRows.add(searchItemResultRow2);
        searchItemResultRows.add(searchItemResultRow3);
        searchItemResultRows.add(searchItemResultRow4);
        searchItemResultRows.add(searchItemResultRow5);

        Collections.sort(searchItemResultRows);
        assertEquals("v. 100", searchItemResultRows.get(0).getChronologyAndEnum());
        assertEquals("v. 111-112", searchItemResultRows.get(1).getChronologyAndEnum());
        assertEquals("v. 113-114", searchItemResultRows.get(2).getChronologyAndEnum());
        assertEquals("v. 116-117", searchItemResultRows.get(3).getChronologyAndEnum());
        assertEquals("v. 120", searchItemResultRows.get(4).getChronologyAndEnum());
    }

    private SearchItemResultRow buildSearchItemResultRow(String callNumber, String chronAndEnum, String customerCode, String barcode, String useRestriction, String collGroupDesg, String availability) {
        SearchItemResultRow searchItemResultRow = new SearchItemResultRow();
        searchItemResultRow.setCallNumber(callNumber);
        searchItemResultRow.setChronologyAndEnum(chronAndEnum);
        searchItemResultRow.setCustomerCode(customerCode);
        searchItemResultRow.setBarcode(barcode);
        searchItemResultRow.setUseRestriction(useRestriction);
        searchItemResultRow.setCollectionGroupDesignation(collGroupDesg);
        searchItemResultRow.setAvailability(availability);
        return searchItemResultRow;
    }
}
