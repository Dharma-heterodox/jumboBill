package org.recap.util;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.marc4j.marc.Record;
import org.recap.BaseTestCase;
import org.recap.repository.jpa.BibliographicDetailsRepository;
import org.recap.repository.jpa.HoldingsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by premkb on 1/8/16.
 */
public class BibJSONUtilUT extends BaseTestCase{

    @Autowired
    BibliographicDetailsRepository bibliographicDetailsRepository;

    @Autowired
    HoldingsDetailsRepository holdingsDetailsRepository;

    private String holdingContent = "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
            "            <record>\n" +
            "              <datafield tag=\"852\" ind1=\"0\" ind2=\"1\">\n" +
            "                <subfield code=\"b\">off,che</subfield>\n" +
            "                <subfield code=\"h\">TA434 .S15</subfield>\n" +
            "              </datafield>\n" +
            "              <datafield tag=\"866\" ind1=\"0\" ind2=\"0\">\n" +
            "                <subfield code=\"a\">v.1-16         </subfield>\n" +
            "              </datafield>\n" +
            "            </record>\n" +
            "          </collection>";

    private String bibContent = "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
            "          <record>\n" +
            "            <controlfield tag=\"001\">NYPG001000011-B</controlfield>\n" +
            "            <controlfield tag=\"005\">20001116192418.8</controlfield>\n" +
            "            <controlfield tag=\"008\">841106s1976    le       b    000 0 arax </controlfield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"010\">\n" +
            "              <subfield code=\"a\">79971032</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"035\">\n" +
            "              <subfield code=\"a\">NNSZ00100011</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"035\">\n" +
            "              <subfield code=\"a\">(OCoLC)ocm004417290 </subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"040\">\n" +
            "              <subfield code=\"c\">NN</subfield>\n" +
            "              <subfield code=\"d\">NN</subfield>\n" +
            "              <subfield code=\"d\">WaOLN</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"043\">\n" +
            "              <subfield code=\"a\">a-ba---</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\"0\" ind2=\"0\" tag=\"050\">\n" +
            "              <subfield code=\"a\">DS247.B28</subfield>\n" +
            "              <subfield code=\"b\">R85</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\"1\" ind2=\" \" tag=\"100\">\n" +
            "              <subfield code=\"a\">Rumayḥī, Muḥammad Ghānim.</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\" \" tag=\"110\">\n" +
            "              <subfield code=\"a\">Rumayḥī, Muḥammad</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\" \" tag=\"111\">\n" +
            "              <subfield code=\"a\">Rumayḥī</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\" \" tag=\"700\">\n" +
            "              <subfield code=\"a\">Yūsuf, Ṣābir.</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\" \" tag=\"710\">\n" +
            "              <subfield code=\"a\">Yūsuf,</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\" \" tag=\"711\">\n" +
            "              <subfield code=\"a\">YūsufṢābir.</subfield>\n" +
            "            </datafield>\t\n" +
            "            <datafield ind1=\"1\" ind2=\"3\" tag=\"245\">\n" +
            "              <subfield code=\"a\">al-Baḥrayn :</subfield>\n" +
            "              <subfield code=\"b\">mushkilāt al-taghyīr al-siyāsī wa-al-ijtimāʻī /</subfield>\n" +
            "              <subfield code=\"c\">Muḥammad al-Rumayḥī.</subfield>\n" +
            "            </datafield>\n" +
            "    <datafield ind1=\"1\" ind2=\"3\" tag=\"130\">\n" +
            "              <subfield code=\"1\">al-Baḥrayn :</subfield>\n" +
            "            </datafield>\n" +
            "   <datafield ind1=\"1\" ind2=\"3\" tag=\"730\">\n" +
            "              <subfield code=\"a\">Muḥammad al-Rumayḥī.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"250\">\n" +
            "              <subfield code=\"a\">al-Ṭabʻah 1.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"260\">\n" +
            "              <subfield code=\"a\">[Bayrūt] :</subfield>\n" +
            "              <subfield code=\"b\">Dār Ibn Khaldūn,</subfield>\n" +
            "              <subfield code=\"c\">1976.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"300\">\n" +
            "              <subfield code=\"a\">264 p. ;</subfield>\n" +
            "              <subfield code=\"c\">24 cm.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"504\">\n" +
            "              <subfield code=\"a\">Includes bibliographies.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"546\">\n" +
            "              <subfield code=\"a\">In Arabic.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\"0\" tag=\"651\">\n" +
            "              <subfield code=\"a\">Bahrain</subfield>\n" +
            "              <subfield code=\"x\">History</subfield>\n" +
            "              <subfield code=\"y\">20th century.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\"0\" tag=\"651\">\n" +
            "              <subfield code=\"a\">Bahrain</subfield>\n" +
            "              <subfield code=\"x\">Economic conditions.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\"0\" tag=\"651\">\n" +
            "              <subfield code=\"a\">Bahrain</subfield>\n" +
            "              <subfield code=\"x\">Social conditions.</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"907\">\n" +
            "              <subfield code=\"a\">.b100000241</subfield>\n" +
            "              <subfield code=\"c\">m</subfield>\n" +
            "              <subfield code=\"d\">a</subfield>\n" +
            "              <subfield code=\"e\">-</subfield>\n" +
            "              <subfield code=\"f\">ara</subfield>\n" +
            "              <subfield code=\"g\">le </subfield>\n" +
            "              <subfield code=\"h\">3</subfield>\n" +
            "              <subfield code=\"i\">1</subfield>\n" +
            "            </datafield>\n" +
            "            <datafield ind1=\" \" ind2=\" \" tag=\"952\">\n" +
            "              <subfield code=\"h\">*OFK 84-1944</subfield>\n" +
            "            </datafield>\n" +
            "          </record>\n" +
            "        </collection>";

    @Test
    public void testLccnTrimValue() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        String lccnValue = bibJSONUtil.getLCCNValue(marcRecord);
        assertEquals(lccnValue, "79971032");
    }

    @Test
    public void testTitleDisplayValue() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        String titleDisplay = bibJSONUtil.getTitleDisplay(marcRecord);
        assertEquals(titleDisplay, "al-Baḥrayn : mushkilāt al-taghyīr al-siyāsī wa-al-ijtimāʻī / Muḥammad al-Rumayḥī.");
    }

    @Test
    public void testAuthorDisplayValue() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        String authorDisplayValue = bibJSONUtil.getAuthorDisplayValue(marcRecord);
        assertEquals(authorDisplayValue, "Rumayḥī, Muḥammad Ghānim. Rumayḥī, Muḥammad Rumayḥī ");
    }

    @Test
    public void testAuthorSearchValue() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        List<String> authorSearchValue = bibJSONUtil.getAuthorSearchValue(marcRecord);
        assertNotNull(authorSearchValue);
        assertEquals(authorSearchValue.size(), 6);
        assertTrue(authorSearchValue.contains("Rumayḥī, Muḥammad Ghānim."));
        assertTrue(authorSearchValue.contains("Rumayḥī, Muḥammad"));
        assertTrue(authorSearchValue.contains("Rumayḥī"));
        assertTrue(authorSearchValue.contains("Yūsuf, Ṣābir."));
        assertTrue(authorSearchValue.contains("Yūsuf,"));
        assertTrue(authorSearchValue.contains("YūsufṢābir."));
    }

    @Test
    public void testTitles() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        String titles = bibJSONUtil.getTitle(marcRecord);
        assertNotNull(titles);
        assertEquals(titles,"al-Baḥrayn : mushkilāt al-taghyīr al-siyāsī wa-al-ijtimāʻī /   Muḥammad al-Rumayḥī.   ");
    }

    @Test
    public void testTitleSort() throws Exception {
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        String titleSort = bibJSONUtil.getTitleSort(marcRecord, bibJSONUtil.getTitleDisplay(marcRecord));
        assertNotNull(titleSort);
        assertEquals(titleSort,"Baḥrayn : mushkilāt al-taghyīr al-siyāsī wa-al-ijtimāʻī /");
    }

    @Test
    public void testStripStart() throws Exception {
        String number = "0023450";
        number = StringUtils.stripStart(number, "0");
        assertEquals("23450", number);
    }
}
