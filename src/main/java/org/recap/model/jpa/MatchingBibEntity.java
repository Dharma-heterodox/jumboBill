package org.recap.model.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by angelind on 31/10/16.
 */
@Entity
@Table(name = "MATCHING_BIB_T", schema = "RECAP", catalog = "")
public class MatchingBibEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROOT")
    private String root;

    @Column(name = "BIB_ID")
    private Integer bibId;

    @Column(name = "OWNING_INSTITUTION")
    private String owningInstitution;

    @Column(name = "OWNING_INST_BIB_ID")
    private String owningInstBibId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "OCLC")
    private String oclc;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "ISSN")
    private String issn;

    @Column(name = "LCCN")
    private String lccn;

    @Column(name = "MATERIAL_TYPE")
    private String materialType;

    @Column(name = "MATCHING")
    private String matching;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getBibId() {
        return bibId;
    }

    public void setBibId(Integer bibId) {
        this.bibId = bibId;
    }

    public String getOwningInstitution() {
        return owningInstitution;
    }

    public void setOwningInstitution(String owningInstitution) {
        this.owningInstitution = owningInstitution;
    }

    public String getOwningInstBibId() {
        return owningInstBibId;
    }

    public void setOwningInstBibId(String owningInstBibId) {
        this.owningInstBibId = owningInstBibId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOclc() {
        return oclc;
    }

    public void setOclc(String oclc) {
        this.oclc = oclc;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getLccn() {
        return lccn;
    }

    public void setLccn(String lccn) {
        this.lccn = lccn;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMatching() {
        return matching;
    }

    public void setMatching(String matching) {
        this.matching = matching;
    }
}
