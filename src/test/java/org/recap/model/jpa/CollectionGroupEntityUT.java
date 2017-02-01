package org.recap.model.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 22/6/16.
 */
public class CollectionGroupEntityUT extends BaseTestCase{

    @Test
    public void collectionGroupEntity(){
        CollectionGroupEntity collectionGroupEntity = new CollectionGroupEntity();
        collectionGroupEntity.setCollectionGroupCode("public");
        collectionGroupEntity.setCollectionGroupDescription("public");
        collectionGroupEntity.setCreatedDate(new Date());
        collectionGroupEntity.setLastUpdatedDate(new Date());
        CollectionGroupEntity entity = collectionGroupDetailRepository.save(collectionGroupEntity);
        assertNotNull(entity);
        System.out.println("collection group id -->"+entity.getCollectionGroupId());
        System.out.println("collection group created date-->"+entity.getCreatedDate());
        assertEquals(entity.getCollectionGroupCode(),"public");
        assertEquals(entity.getCollectionGroupDescription(),"public");
        assertEquals(collectionGroupEntity.getLastUpdatedDate().toString(),new Date().toString());
        collectionGroupDetailRepository.delete(collectionGroupEntity);
    }

}