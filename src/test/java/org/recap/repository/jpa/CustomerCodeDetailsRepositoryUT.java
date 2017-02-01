package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.CustomerCodeEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 18/10/16.
 */
public class CustomerCodeDetailsRepositoryUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findByCustomerCode() throws Exception {
        CustomerCodeEntity customerCodeEntity = getCustomerCodeEntity("ZZ", "Desc ZZ", 3, "ZZ,YY");
        CustomerCodeEntity saveCustomerCodeEntity = customerCodeDetailsRepository.saveAndFlush(customerCodeEntity);
        entityManager.refresh(saveCustomerCodeEntity);
        assertNotNull(saveCustomerCodeEntity);
        assertNotNull(saveCustomerCodeEntity.getCustomerCodeId());
        assertNotNull(saveCustomerCodeEntity.getCustomerCode());

        CustomerCodeEntity byCustomerCode = customerCodeDetailsRepository.findByCustomerCode(saveCustomerCodeEntity.getCustomerCode());
        assertNotNull(byCustomerCode);
        assertNotNull(byCustomerCode.getCustomerCode());
        assertEquals("ZZ", byCustomerCode.getCustomerCode());
        assertNotNull(byCustomerCode.getInstitutionEntity());
    }

    @Test
    public void findByCustomerCodeIn() throws Exception {
        CustomerCodeEntity customerCodeEntity1 = getCustomerCodeEntity("ZZ", "Desc ZZ", 3, "ZZ,YY");
        CustomerCodeEntity saveCustomerCodeEntity1 = customerCodeDetailsRepository.saveAndFlush(customerCodeEntity1);
        entityManager.refresh(saveCustomerCodeEntity1);
        assertNotNull(saveCustomerCodeEntity1);
        assertNotNull(saveCustomerCodeEntity1.getCustomerCodeId());
        assertNotNull(saveCustomerCodeEntity1.getCustomerCode());

        CustomerCodeEntity customerCodeEntity2 = getCustomerCodeEntity("YY", "Desc YY", 3, "ZZ,YY");
        CustomerCodeEntity saveCustomerCodeEntity2 = customerCodeDetailsRepository.saveAndFlush(customerCodeEntity2);
        entityManager.refresh(saveCustomerCodeEntity2);
        assertNotNull(saveCustomerCodeEntity2);
        assertNotNull(saveCustomerCodeEntity2.getCustomerCodeId());
        assertNotNull(saveCustomerCodeEntity2.getCustomerCode());

        List<CustomerCodeEntity> byCustomerCodeIn = customerCodeDetailsRepository.findByCustomerCodeIn(Arrays.asList("ZZ", "YY"));
        assertNotNull(byCustomerCodeIn);
        assertEquals(2, byCustomerCodeIn.size());
        assertNotNull(byCustomerCodeIn.get(0));
        assertEquals("YY", byCustomerCodeIn.get(0).getCustomerCode());
        assertNotNull(byCustomerCodeIn.get(1));
        assertEquals("ZZ", byCustomerCodeIn.get(1).getCustomerCode());
    }

    private CustomerCodeEntity getCustomerCodeEntity(String customerCode, String description, Integer institutionId, String deliveryRestrictions) {
        CustomerCodeEntity customerCodeEntity = new CustomerCodeEntity();
        customerCodeEntity.setCustomerCode(customerCode);
        customerCodeEntity.setDescription(description);
        customerCodeEntity.setOwningInstitutionId(institutionId);
        customerCodeEntity.setDeliveryRestrictions(deliveryRestrictions);
        return customerCodeEntity;
    }
}
