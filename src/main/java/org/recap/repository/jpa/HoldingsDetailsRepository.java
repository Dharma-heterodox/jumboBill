package org.recap.repository.jpa;

import org.recap.model.jpa.HoldingsEntity;
import org.recap.model.jpa.HoldingsPK;
import org.recap.model.jpa.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hemalathas on 21/6/16.
 */
@RepositoryRestResource(collectionResourceRel = "holdings", path = "holdings")
public interface HoldingsDetailsRepository extends JpaRepository<HoldingsEntity, HoldingsPK> {

    Long countByIsDeletedFalse();

    HoldingsEntity findByHoldingsId(Integer holdingsId);

    Page<HoldingsEntity> findAllByIsDeletedFalse(Pageable pageable);

    Long countByOwningInstitutionIdAndIsDeletedFalse(Integer owningInstitutionId);

    Page<HoldingsEntity> findByOwningInstitutionIdAndIsDeletedFalse(Pageable pageable, Integer institutionId);

    List<ItemEntity> getNonDeletedItemEntities(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionHoldingsId") String owningInstitutionHoldingsId);

    @Query(value = "SELECT COUNT(*) FROM ITEM_T, ITEM_HOLDINGS_T WHERE ITEM_HOLDINGS_T.ITEM_INST_ID = ITEM_T.OWNING_INST_ID AND " +
            "ITEM_HOLDINGS_T.OWNING_INST_ITEM_ID = ITEM_T.OWNING_INST_ITEM_ID AND ITEM_T.IS_DELETED = 0 AND " +
            "ITEM_HOLDINGS_T.OWNING_INST_HOLDINGS_ID = :owningInstitutionHoldingsId AND ITEM_HOLDINGS_T.HOLDINGS_INST_ID = :owningInstitutionId", nativeQuery = true)
    Long getNonDeletedItemsCount(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionHoldingsId") String owningInstitutionHoldingsId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE HoldingsEntity holdings SET holdings.isDeleted = true, holdings.lastUpdatedBy = :lastUpdatedBy, holdings.lastUpdatedDate = :lastUpdatedDate WHERE holdings.holdingsId IN :holdingIds")
    int markHoldingsAsDeleted(@Param("holdingIds") List<Integer> holdingIds, @Param("lastUpdatedBy") String lastUpdatedBy, @Param("lastUpdatedDate") Date lastUpdatedDate);
}
