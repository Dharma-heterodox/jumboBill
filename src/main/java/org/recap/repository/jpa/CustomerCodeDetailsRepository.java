package org.recap.repository.jpa;

import org.recap.model.jpa.CustomerCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by rajeshbabuk on 18/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "customerCode", path = "customerCode")
public interface CustomerCodeDetailsRepository extends JpaRepository<CustomerCodeEntity, Integer> {

    CustomerCodeEntity findByCustomerCode(@Param("customerCode") String customerCode);

    CustomerCodeEntity findByDescription(@Param("description") String description);

    List<CustomerCodeEntity> findByCustomerCodeIn(List<String> customerCodes);
}
