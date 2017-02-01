package org.recap.model.search;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by premkb on 2/8/16.
 */
public class SearchResultRowUT {

    @Test
    public void testSearchResultRow() throws Exception {
        SearchResultRow searchResultRow = new SearchResultRow();
        setSearchResultRow(searchResultRow);
        assertNotNull(searchResultRow);
        assertEquals("TestTitle", searchResultRow.getTitle());
        assertEquals("TestAuthor", searchResultRow.getAuthor());
        assertEquals("NYPL", searchResultRow.getOwningInstitution());
    }

    private void setSearchResultRow(SearchResultRow searchResultRow) {
        searchResultRow.setTitle("TestTitle");
        searchResultRow.setAuthor("TestAuthor");
        searchResultRow.setOwningInstitution("NYPL");
    }
}
