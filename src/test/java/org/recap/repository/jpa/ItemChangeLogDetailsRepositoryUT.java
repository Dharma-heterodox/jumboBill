package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.ItemChangeLogEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 18/10/16.
 */
public class ItemChangeLogDetailsRepositoryUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveItemChangeLogEntity() throws Exception {
        ItemChangeLogEntity itemChangeLogEntity = new ItemChangeLogEntity();
        itemChangeLogEntity.setUpdatedBy("guest");
        itemChangeLogEntity.setUpdatedDate(new Date());
        itemChangeLogEntity.setOperationType("Test");
        itemChangeLogEntity.setNotes("Test Notes");

        ItemChangeLogEntity savedItemChangeLogEntity = itemChangeLogDetailsRepository.save(itemChangeLogEntity);
        entityManager.refresh(savedItemChangeLogEntity);
        assertNotNull(savedItemChangeLogEntity);
        assertNotNull(savedItemChangeLogEntity.getChangeLogId());
    }

    @Test
    public void checkfindByRecordId() throws Exception{
        ItemChangeLogEntity itemChangeLogEntity = saveDeaccessionNotes();
        ItemChangeLogEntity byRecordId = itemChangeLogDetailsRepository.findByRecordIdAndOperationType(itemChangeLogEntity.getRecordId(),"Deaccession");
        assertNotNull(byRecordId);
        if (itemChangeLogEntity.getOperationType().equalsIgnoreCase("Deaccession")){
            assertEquals("testing",byRecordId.getNotes());
        }

    }

    private ItemChangeLogEntity saveDeaccessionNotes() throws Exception{
        ItemChangeLogEntity itemChangeLogEntity = new ItemChangeLogEntity();
        itemChangeLogEntity.setUpdatedBy("guest");
        itemChangeLogEntity.setUpdatedDate(new Date());
        itemChangeLogEntity.setOperationType("Deaccession");
        itemChangeLogEntity.setNotes("testing");
        ItemChangeLogEntity savedItemChangeLogEntity = itemChangeLogDetailsRepository.save(itemChangeLogEntity);
        entityManager.refresh(savedItemChangeLogEntity);
        return itemChangeLogEntity;

    }


}
