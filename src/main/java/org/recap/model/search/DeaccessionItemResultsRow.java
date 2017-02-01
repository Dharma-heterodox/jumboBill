package org.recap.model.search;

/**
 * Created by akulak on 23/12/16.
 */
public class DeaccessionItemResultsRow {

    private Integer itemId;
    private String deaccessionDate;
    private String title;
    private String deaccessionOwnInst;
    private String itemBarcode;
    private String cgd;
    private String deaccessionNotes;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getDeaccessionDate() {
        return deaccessionDate;
    }

    public void setDeaccessionDate(String deaccessionDate) {
        this.deaccessionDate = deaccessionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeaccessionOwnInst() {
        return deaccessionOwnInst;
    }

    public void setDeaccessionOwnInst(String deaccessionOwnInst) {
        this.deaccessionOwnInst = deaccessionOwnInst;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getCgd() {
        return cgd;
    }

    public void setCgd(String cgd) {
        this.cgd = cgd;
    }

    public String getDeaccessionNotes() {
        return deaccessionNotes;
    }

    public void setDeaccessionNotes(String deaccessionNotes) {
        this.deaccessionNotes = deaccessionNotes;
    }
}
