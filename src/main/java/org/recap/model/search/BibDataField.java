package org.recap.model.search;

/**
 * Created by rajeshbabuk on 25/7/16.
 */
public class BibDataField {

    private String dataFieldTag;
    private char indicator1;
    private char indicator2;
    private String dataFieldValue;

    public String getDataFieldTag() {
        return dataFieldTag;
    }

    public void setDataFieldTag(String dataFieldTag) {
        this.dataFieldTag = dataFieldTag;
    }

    public char getIndicator1() {
        return indicator1;
    }

    public void setIndicator1(char indicator1) {
        this.indicator1 = indicator1;
    }

    public char getIndicator2() {
        return indicator2;
    }

    public void setIndicator2(char indicator2) {
        this.indicator2 = indicator2;
    }

    public String getDataFieldValue() {
        return dataFieldValue;
    }

    public void setDataFieldValue(String dataFieldValue) {
        this.dataFieldValue = dataFieldValue;
    }
}
