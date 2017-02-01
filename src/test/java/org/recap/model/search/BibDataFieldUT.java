package org.recap.model.search;

import org.junit.Test;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.recap.util.BibJSONUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by premkb on 2/8/16.
 */
public class BibDataFieldUT {

    private String bibContent = "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n"+
            "                <record>\n"+
            "                    <controlfield tag=\"001\">NYPG002000036-B</controlfield>\n"+
            "                    <controlfield tag=\"005\">20001116192424.2</controlfield>\n"+
            "                    <controlfield tag=\"008\">850225r19731907nyu b 001 0 ara</controlfield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"010\">\n"+
            "                        <subfield code=\"a\">77173005</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"040\">\n"+
            "                        <subfield code=\"c\">NN</subfield>\n"+
            "                        <subfield code=\"d\">NN</subfield>\n"+
            "                        <subfield code=\"d\">CStRLIN</subfield>\n"+
            "                        <subfield code=\"d\">WaOLN</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"043\">\n"+
            "                        <subfield code=\"a\">ff-----</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\"0\" tag=\"050\">\n"+
            "                        <subfield code=\"a\">DS36.6</subfield>\n"+
            "                        <subfield code=\"b\">.I26 1973</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\"0\" tag=\"082\">\n"+
            "                        <subfield code=\"a\">910.031/767</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"100\">\n"+
            "                        <subfield code=\"a\">Ibn Jubayr, MuhÌ£ammad ibn AhÌ£mad,</subfield>\n"+
            "                        <subfield code=\"d\">1145-1217.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\"0\" tag=\"245\">\n"+
            "                        <subfield code=\"a\">RihÌ£lat</subfield>\n"+
            "                        <subfield code=\"b\">AbÄ« al-Husayn Muhammad ibn Ahmad ibn Jubayr al-KinÄ\u0081nÄ« al-AndalusÄ«\n"+
            "                            al-BalinsÄ«.\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"250\">\n"+
            "                        <subfield code=\"a\">2d ed.</subfield>\n"+
            "                        <subfield code=\"b\">rev. by M. J. de Goeje and printed for the Trustees of the \"E. J. W. Gibb\n"+
            "                            memorial\"\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"260\">\n"+
            "                        <subfield code=\"a\">[New York,</subfield>\n"+
            "                        <subfield code=\"b\">AMS Press,</subfield>\n"+
            "                        <subfield code=\"c\">1973] 1907.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"300\">\n"+
            "                        <subfield code=\"a\">363, 53 p.</subfield>\n"+
            "                        <subfield code=\"c\">23 cm.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"500\">\n"+
            "                        <subfield code=\"a\">Added t.p.: The travels of Ibn Jubayr. Edited from a ms. in the University\n"+
            "                            Library of Leyden by William Wright.\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"500\">\n"+
            "                        <subfield code=\"a\">Original ed. issued as v. 5 of \"E.J.W. Gibb memorial\" series.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"504\">\n"+
            "                        <subfield code=\"a\">Includes bibliographical references and index.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\"0\" tag=\"651\">\n"+
            "                        <subfield code=\"a\">Islamic Empire</subfield>\n"+
            "                        <subfield code=\"x\">Description and travel.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"700\">\n"+
            "                        <subfield code=\"a\">Wright, William,</subfield>\n"+
            "                        <subfield code=\"d\">1830-1889.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"700\">\n"+
            "                        <subfield code=\"a\">Goeje, M. J. de</subfield>\n"+
            "                        <subfield code=\"q\">(Michael Jan),</subfield>\n"+
            "                        <subfield code=\"d\">1836-1909.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\" \" tag=\"740\">\n"+
            "                        <subfield code=\"a\">Travels of Ibn Jubayr.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\"0\" tag=\"830\">\n"+
            "                        <subfield code=\"a\">\"E.J.W. Gibb memorial\" series ;</subfield>\n"+
            "                        <subfield code=\"v\">v.5.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"907\">\n"+
            "                        <subfield code=\"a\">.b100006279</subfield>\n"+
            "                        <subfield code=\"c\">m</subfield>\n"+
            "                        <subfield code=\"d\">a</subfield>\n"+
            "                        <subfield code=\"e\">-</subfield>\n"+
            "                        <subfield code=\"f\">ara</subfield>\n"+
            "                        <subfield code=\"g\">nyu</subfield>\n"+
            "                        <subfield code=\"h\">0</subfield>\n"+
            "                        <subfield code=\"i\">3</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"952\">\n"+
            "                        <subfield code=\"h\">*OAC (\"E. J. W. Gibb memorial\" series. v. 5)</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"952\">\n"+
            "                        <subfield code=\"h\">*OFV 87-659</subfield>\n"+
            "                    </datafield>\n"+
            "                    <leader>01814cam a2200409 450000</leader>\n"+
            "                </record>\n"+
            "            </collection>";

    @Test
    public void buildBibFields()throws Exception{
        BibJSONUtil bibJSONUtil = new BibJSONUtil();
        List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
        Record marcRecord = records.get(0);
        BibliographicMarcForm bibliographicMarcForm = buildBibliographicMarcForm(marcRecord, bibJSONUtil);
        assertNotNull(bibliographicMarcForm);
        assertNotNull(bibliographicMarcForm.getBibDataFields());
        assertEquals("010", bibliographicMarcForm.getBibDataFields().get(0).getDataFieldTag());
        assertEquals("_",String.valueOf(bibliographicMarcForm.getBibDataFields().get(0).getIndicator1()));
        assertEquals("_",String.valueOf(bibliographicMarcForm.getBibDataFields().get(0).getIndicator2()));
        assertEquals("|a 77173005 ", bibliographicMarcForm.getBibDataFields().get(0).getDataFieldValue());

    }

    private BibliographicMarcForm buildBibliographicMarcForm(Record marcRecord, BibJSONUtil bibJSONUtil) {
        BibliographicMarcForm bibliographicMarcForm = new BibliographicMarcForm();
        bibliographicMarcForm.setTitle(bibJSONUtil.getTitle(marcRecord));
        bibliographicMarcForm.setAuthor(bibJSONUtil.getAuthorDisplayValue(marcRecord));
        bibliographicMarcForm.setPublisher(bibJSONUtil.getPublisherValue(marcRecord));
        bibliographicMarcForm.setPublishedDate(bibJSONUtil.getPublicationDateValue(marcRecord));
        bibliographicMarcForm.setTag000(bibJSONUtil.getLeader(marcRecord));
        bibliographicMarcForm.setControlNumber001(bibJSONUtil.getControlFieldValue(marcRecord, "001"));
        bibliographicMarcForm.setControlNumber005(bibJSONUtil.getControlFieldValue(marcRecord, "005"));
        bibliographicMarcForm.setControlNumber008(bibJSONUtil.getControlFieldValue(marcRecord, "008"));
        bibliographicMarcForm.setBibDataFields(buildBibDataFields(marcRecord));
        return bibliographicMarcForm;
    }

    private List<BibDataField> buildBibDataFields(Record marcRecord) {
        List<BibDataField> bibDataFields = new ArrayList<>();
        List<DataField> marcDataFields = marcRecord.getDataFields();
        if (!CollectionUtils.isEmpty(marcDataFields)) {
            for (DataField marcDataField : marcDataFields) {
                BibDataField bibDataField = new BibDataField();
                bibDataField.setDataFieldTag(marcDataField.getTag());
                if (Character.isWhitespace(marcDataField.getIndicator1())) {
                    bibDataField.setIndicator1('_');
                } else {
                    bibDataField.setIndicator1(marcDataField.getIndicator1());
                }
                if (Character.isWhitespace(marcDataField.getIndicator2())) {
                    bibDataField.setIndicator2('_');
                } else {
                    bibDataField.setIndicator2(marcDataField.getIndicator2());
                }
                List<Subfield> subfields = marcDataField.getSubfields();
                if (!CollectionUtils.isEmpty(subfields)) {
                    StringBuffer buffer = new StringBuffer();
                    for (Subfield subfield : subfields) {
                        buffer.append("|").append(subfield.getCode());
                        buffer.append(" ").append(subfield.getData()).append(" ");
                    }
                    bibDataField.setDataFieldValue(buffer.toString());
                }
                bibDataFields.add(bibDataField);
            }
        }
        return bibDataFields;
    }
}
