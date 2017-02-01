package org.recap.repository.jpa;

import org.recap.model.jpa.CollectionGroupEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by hemalathas on 22/6/16.
 */
public interface CollectionGroupDetailsRepository extends PagingAndSortingRepository<CollectionGroupEntity, Integer> {

    CollectionGroupEntity findByCollectionGroupCode(String collectionGroupCode);
}
