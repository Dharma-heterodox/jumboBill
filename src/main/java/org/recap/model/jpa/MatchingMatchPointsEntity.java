package org.recap.model.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by angelind on 27/10/16.
 */
@Entity
@Table(name = "MATCHING_MATCHPOINTS_T", schema = "RECAP", catalog = "")
public class MatchingMatchPointsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MATCH_CRITERIA")
    private String matchCriteria;

    @Column(name = "CRITERIA_VALUE")
    private String criteriaValue;

    @Column(name = "CRITERIA_VALUE_COUNT")
    private Integer criteriaValueCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchCriteria() {
        return matchCriteria;
    }

    public void setMatchCriteria(String matchCriteria) {
        this.matchCriteria = matchCriteria;
    }

    public String getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(String criteriaValue) {
        this.criteriaValue = criteriaValue;
    }

    public Integer getCriteriaValueCount() {
        return criteriaValueCount;
    }

    public void setCriteriaValueCount(Integer criteriaValueCount) {
        this.criteriaValueCount = criteriaValueCount;
    }
}
