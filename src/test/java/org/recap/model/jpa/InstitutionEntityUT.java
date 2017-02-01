package org.recap.model.jpa;

import org.hibernate.validator.constraints.SafeHtml;
import org.junit.Test;
import org.recap.BaseTestCase;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 22/6/16.
 */
public class InstitutionEntityUT extends BaseTestCase {

    @Test
    public void institutionEntity(){
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionCode("UC");
        institutionEntity.setInstitutionName("University of Chicago");
        InstitutionEntity entity = institutionDetailRepository.save(institutionEntity);
        assertNotNull(entity);
        System.out.println("Institution Id-->"+entity.getInstitutionId());
        assertEquals(entity.getInstitutionCode(),"UC");
        assertEquals(entity.getInstitutionName(),"University of Chicago");
        institutionDetailRepository.delete(institutionEntity);
    }

}