package org.recap.repository.jpa;

import org.recap.model.jpa.ItemStatusEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemalathas on 22/6/16.
 */
@RepositoryRestResource(collectionResourceRel = "itemStatus", path = "itemStatus")
public interface ItemStatusDetailsRepository extends PagingAndSortingRepository<ItemStatusEntity, Integer> {

    ItemStatusEntity findByStatusCode(String statusCode);

    ItemStatusEntity findByItemStatusId(@Param("itemStatusId") Integer itemStatusId);
}
