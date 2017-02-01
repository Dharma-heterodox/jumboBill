package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pvsubrah on 6/22/16.
 */
public class InstitutionDetailsRepositoryUT extends BaseTestCase {

    @Autowired
    InstitutionDetailsRepository institutionDetailsRepository;

    @Test
    public void saveAndFind() throws Exception {
        assertNotNull(institutionDetailsRepository);

        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionCode("test");
        institutionEntity.setInstitutionName("test");

        InstitutionEntity savedInstitutionEntity = institutionDetailsRepository.save(institutionEntity);
        assertNotNull(savedInstitutionEntity);
        assertNotNull(savedInstitutionEntity.getInstitutionId());
        assertEquals(savedInstitutionEntity.getInstitutionCode(), "test");
        assertEquals(savedInstitutionEntity.getInstitutionName(), "test");

        InstitutionEntity byInstitutionCode = institutionDetailsRepository.findByInstitutionCode("test");
        assertNotNull(byInstitutionCode);

        InstitutionEntity byInstitutionName = institutionDetailsRepository.findByInstitutionName("test");
        assertNotNull(byInstitutionName);
    }

}