package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.ItemStatusEntity;
import org.recap.repository.jpa.ItemStatusDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by chenchulakshmig on 14/7/16.
 */
public class ItemStatusDetailsRepositoryUT extends BaseTestCase {

    @Autowired
    ItemStatusDetailsRepository itemStatusDetailsRepository;

    @Test
    public void saveAndFind() throws Exception {
        assertNotNull(itemStatusDetailsRepository);

        ItemStatusEntity itemStatusEntity = new ItemStatusEntity();
        itemStatusEntity.setStatusCode("test");
        itemStatusEntity.setStatusDescription("test");

        ItemStatusEntity savedItemStatusEntity = itemStatusDetailsRepository.save(itemStatusEntity);
        assertNotNull(savedItemStatusEntity);
        assertNotNull(savedItemStatusEntity.getItemStatusId());
        assertEquals(itemStatusEntity.getStatusCode(), "test");
        assertEquals(itemStatusEntity.getStatusDescription(), "test");

        ItemStatusEntity byStatusCode = itemStatusDetailsRepository.findByStatusCode("test");
        assertNotNull(byStatusCode);
    }

    @Test
    public void testItemStatus(){
        ItemStatusEntity itemStatusEntity = itemStatusDetailsRepository.findByItemStatusId(1);
        assertNotNull(itemStatusEntity);
        assertEquals(itemStatusEntity.getStatusCode(),"Available");
    }

}