package org.recap;

import junit.framework.Assert;
import org.junit.Test;
import org.marc4j.marc.*;
import org.recap.model.jpa.BibliographicEntity;
import org.recap.util.MarcUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DiacriticalMarksUT extends BaseTestCase {

    @Test
    public void getXmlContent() throws Exception{
        String diacriticalText = "";
        String text = "";
        String bibContent =
                "                <collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
                "                    <record>\n" +
                "                        <leader>01235cas a22003491  4500</leader>\n" +
                "                        <controlfield tag=\"001\">288871</controlfield>\n" +
                "                        <controlfield tag=\"003\">NNC</controlfield>\n" +
                "                        <controlfield tag=\"005\">20100224230432.0</controlfield>\n" +
                "                        <controlfield tag=\"008\">840208c19479999gw qn p       0   a0ger d</controlfield>\n" +
                "                        <datafield tag=\"010\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">   58047354 </subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"022\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">0003-9500</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">(OCoLC)3778607</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">(OCoLC)ocm03778607</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">(CStRLIN)NYCG84-S321</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"9\">ABG3267CU</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">(NNC)288871</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"040\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">CtY</subfield>\n" +
                "                            <subfield code=\"c\">CtY</subfield>\n" +
                "                            <subfield code=\"d\">MiU</subfield>\n" +
                "                            <subfield code=\"d\">MdBJ</subfield>\n" +
                "                            <subfield code=\"d\">NNC</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"050\" ind1=\"0\" ind2=\" \">\n" +
                "                            <subfield code=\"a\">CD9</subfield>\n" +
                "                            <subfield code=\"b\">.A73</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"090\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">CD9</subfield>\n" +
                "                            <subfield code=\"b\">.A73</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"245\" ind1=\"0\" ind2=\"4\">\n" +
                "                            <subfield code=\"a\">Der Archivar.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"260\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">Düsseldorf,</subfield>\n" +
                "                            <subfield code=\"b\">Schmitt [etc.]</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"300\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"c\">30 cm.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"310\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">4 no. a year,</subfield>\n" +
                "                            <subfield code=\"b\">1947-48, 1950-</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"321\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">2 no. a year,</subfield>\n" +
                "                            <subfield code=\"b\">1949.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"362\" ind1=\"0\" ind2=\" \">\n" +
                "                            <subfield code=\"a\">1.-   Jahrg.; Aug. 1947-</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"500\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">\"Mitteilungsblatt für deutsches Archivwesen.\"</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"550\" ind1=\" \" ind2=\" \">\n" +
                "                            <subfield code=\"a\">Vols. for 1947-   \"im Auftrag des Vereins Deutscher Archivare hrsg. vom Staatsarchiv Düsseldorf\"; vols. for &lt;1977-&gt; \"herausgegeben vom Hauptstaatsarchiv Düsseldorf\"; vols. for &lt;1980-&gt; \"herausgegeben vom Nordhein-Westfälischen Hauptstaatsarchiv.\"</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                "                            <subfield code=\"a\">Archives</subfield>\n" +
                "                            <subfield code=\"v\">Periodicals.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"710\" ind1=\"2\" ind2=\" \">\n" +
                "                            <subfield code=\"a\">Verein Deutscher Archivare.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"710\" ind1=\"1\" ind2=\" \">\n" +
                "                            <subfield code=\"a\">North Rhine-Westphalia (Germany).</subfield>\n" +
                "                            <subfield code=\"b\">Staatsarchiv.</subfield>\n" +
                "                        </datafield>\n" +
                "                        <datafield tag=\"710\" ind1=\"2\" ind2=\" \">\n" +
                "                            <subfield code=\"a\">Hauptstaatsarchiv Düsseldorf.</subfield>\n" +
                "                        </datafield>\n" +
                "                    </record>\n" +
                "                </collection>\n";
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent(bibContent.getBytes());
        bibliographicEntity.setOwningInstitutionId(2);
        bibliographicEntity.setOwningInstitutionBibId("288877");
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setLastUpdatedDate(new Date());
        BibliographicEntity savedBibliographicEntity = bibliographicDetailsRepository.save(bibliographicEntity);
        String content = new String(savedBibliographicEntity.getContent());
        MarcUtil marcUtil = new MarcUtil();
        List<Record> records = marcUtil.convertMarcXmlToRecord(content);
        diacriticalText = getText(records);
        List<Record> xmlRecords = marcUtil.convertMarcXmlToRecord(bibContent);
        text = getText(xmlRecords);
        System.out.println("from xml --->" + text);
        System.out.println("from database --->" + diacriticalText);
        Assert.assertEquals(text,diacriticalText);
        bibliographicDetailsRepository.delete(bibliographicEntity);
   }

    public String getText(List<Record> records){
        String text = "";
        for (Iterator<Record> iterator = records.iterator(); iterator.hasNext(); ) {
            Record record = iterator.next();
            List<DataField> dataFields = record.getDataFields();
            for (Iterator<DataField> dataFieldIterator = dataFields.iterator(); dataFieldIterator.hasNext(); ) {
                DataField dataField = dataFieldIterator.next();
                if(dataField.getTag().equals("260")){
                    text = dataField.getSubfields().get(0).getData();
                    break;
                }
            }
        }
        return text;
    }
}
