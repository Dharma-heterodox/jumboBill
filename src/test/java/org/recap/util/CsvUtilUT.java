package org.recap.util;

import com.csvreader.CsvReader;
import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.search.SearchItemResultRow;
import org.recap.model.search.SearchRecordsRequest;
import org.recap.model.search.SearchResultRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by premkb on 29/7/16.
 */
public class CsvUtilUT extends BaseTestCase {

    @Autowired
    private CsvUtil csvUtil;

    @Test
    public void writeSearchResultsToCsv() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileNameWithExtension = "ExportRecords_" + dateFormat.format(new Date()) + ".csv";
        File csvFile = csvUtil.writeSearchResultsToCsv(buildRequestWithResultRows().getSearchResultRows(), fileNameWithExtension);
        CsvReader csvReader = new CsvReader(new FileReader(csvFile), ',');

        assertNotNull(csvFile);
        assertTrue(csvFile.exists());
        assertEquals(fileNameWithExtension, csvFile.getName());

        assertTrue(csvReader.readHeaders());
        assertTrue(csvReader.readRecord());
        assertEquals(csvReader.get("Title"), "Title1");
        assertEquals(csvReader.get("Author"), "Author1");
        assertEquals(csvReader.get("Publisher"), "Publisher1");
        assertEquals(csvReader.get("Publisher Date"), "1981");
        assertEquals(csvReader.get("Owning Institution"), "NYPL");
        assertEquals(csvReader.get("Customer Code"), "Customer Code1");
        assertEquals(csvReader.get("Collection Group Designation"), "Shared");
        assertEquals(csvReader.get("Use Restriction"), "In Library Use");
        assertEquals(csvReader.get("Barcode"), "1");
        assertEquals(csvReader.get("Summary Holdings"), "Summary Holdings 1");

        assertTrue(csvReader.readRecord());
        assertEquals(csvReader.get("Title"), "Title2");
        assertEquals(csvReader.get("Author"), "Author2");
        assertEquals(csvReader.get("Publisher"), "Publisher2");
        assertEquals(csvReader.get("Publisher Date"), "1982");
        assertEquals(csvReader.get("Owning Institution"), "NYPL");
        assertEquals(csvReader.get("Customer Code"), "Customer Code2");
        assertEquals(csvReader.get("Collection Group Designation"), "Shared");
        assertEquals(csvReader.get("Use Restriction"), "In Library Use");
        assertEquals(csvReader.get("Barcode"), "2");
        assertEquals(csvReader.get("Summary Holdings"), "Summary Holdings 2");

        assertTrue(csvReader.readHeaders());
        assertTrue(csvReader.readRecord());
        assertEquals(csvReader.get("Call Number"), "Call Number Item 1");
        assertEquals(csvReader.get("Chronology & Enumeration"), "Chn Enum Item 1");
        assertEquals(csvReader.get("Customer Code"), "Customer Code Item 1");
        assertEquals(csvReader.get("Use Restriction"), "In Library use");
        assertEquals(csvReader.get("Collection Group Designation"), "Shared");
        assertEquals(csvReader.get("Barcode"), "3");
        csvReader.close();
    }

    private SearchRecordsRequest buildRequestWithResultRows() {
        SearchRecordsRequest searchRecordsRequest = new SearchRecordsRequest();
        List<SearchResultRow> searchResultRows = new ArrayList<>();
        SearchResultRow searchResultRow1 = new SearchResultRow();
        searchResultRow1.setTitle("Title1");
        searchResultRow1.setAuthor("Author1");
        searchResultRow1.setPublisher("Publisher1");
        searchResultRow1.setPublisherDate("1981");
        searchResultRow1.setOwningInstitution("NYPL");
        searchResultRow1.setCustomerCode("Customer Code1");
        searchResultRow1.setCollectionGroupDesignation("Shared");
        searchResultRow1.setUseRestriction("In Library Use");
        searchResultRow1.setBarcode("1");
        searchResultRow1.setSummaryHoldings("Summary Holdings 1");
        searchResultRow1.setSelected(true);

        SearchResultRow searchResultRow2 = new SearchResultRow();
        searchResultRow2.setTitle("Title2");
        searchResultRow2.setAuthor("Author2");
        searchResultRow2.setPublisher("Publisher2");
        searchResultRow2.setPublisherDate("1982");
        searchResultRow2.setOwningInstitution("NYPL");
        searchResultRow2.setCustomerCode("Customer Code2");
        searchResultRow2.setCollectionGroupDesignation("Shared");
        searchResultRow2.setUseRestriction("In Library Use");
        searchResultRow2.setBarcode("2");
        searchResultRow2.setSummaryHoldings("Summary Holdings 2");
        searchResultRow2.setSelectAllItems(true);

        List<SearchItemResultRow> searchItemResultRows = new ArrayList<>();
        SearchItemResultRow searchItemResultRow1 = new SearchItemResultRow();
        searchItemResultRow1.setCallNumber("Call Number Item 1");
        searchItemResultRow1.setChronologyAndEnum("Chn Enum Item 1");
        searchItemResultRow1.setCustomerCode("Customer Code Item 1");
        searchItemResultRow1.setBarcode("3");
        searchItemResultRow1.setUseRestriction("In Library use");
        searchItemResultRow1.setCollectionGroupDesignation("Shared");
        searchItemResultRow1.setAvailability("Available");
        searchItemResultRow1.setSelectedItem(true);
        searchItemResultRows.add(searchItemResultRow1);
        searchResultRow2.setSearchItemResultRows(searchItemResultRows);

        searchResultRows.add(searchResultRow1);
        searchResultRows.add(searchResultRow2);
        searchRecordsRequest.setSearchResultRows(searchResultRows);
        return searchRecordsRequest;
    }
}
