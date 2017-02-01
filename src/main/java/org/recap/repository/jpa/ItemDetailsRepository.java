package org.recap.repository.jpa;

import org.recap.model.jpa.ItemEntity;
import org.recap.model.jpa.ItemPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by chenchulakshmig on 21/6/16.
 */
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemDetailsRepository extends PagingAndSortingRepository<ItemEntity, ItemPK> {

    Long countByIsDeletedFalse();

    Page<ItemEntity> findAllByIsDeletedFalse(Pageable pageable);

    ItemEntity findByItemId(Integer itemId);

    Long countByOwningInstitutionIdAndIsDeletedFalse(Integer institutionId);

    Page<ItemEntity> findByOwningInstitutionIdAndIsDeletedFalse(Pageable pageable, Integer institutionId);

    List<ItemEntity> findByOwningInstitutionId(Integer owningInstitutionId);

    ItemEntity findByOwningInstitutionItemId(@Param("owningInstitutionItemId") String owningInstitutionItemId);

    List<ItemEntity> findByBarcode(String barcode);

    List<ItemEntity> findByBarcodeAndIsDeletedFalse(String barcode);

    @Modifying
    @Transactional
    @Query("update ItemEntity item set item.collectionGroupId = :collectionGroupId, item.lastUpdatedBy = :lastUpdatedBy, item.lastUpdatedDate = :lastUpdatedDate where item.itemId = :itemId")
    int updateCollectionGroupIdByItemId(@Param("collectionGroupId") Integer collectionGroupId, @Param("itemId") Integer itemId, @Param("lastUpdatedBy") String lastUpdatedBy, @Param("lastUpdatedDate") Date lastUpdatedDate);

    List<ItemEntity> findByBarcodeIn(@Param("barcodes") List<String> barcodes);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ItemEntity item SET item.isDeleted = true, item.lastUpdatedBy = :lastUpdatedBy, item.lastUpdatedDate = :lastUpdatedDate WHERE item.itemId = :itemId")
    int markItemAsDeleted(@Param("itemId") Integer itemId, @Param("lastUpdatedBy") String lastUpdatedBy, @Param("lastUpdatedDate") Date lastUpdatedDate);

    @Query(value = "select itemStatus.statusCode from ItemEntity item, ItemStatusEntity itemStatus where item.itemAvailabilityStatusId = itemStatus.itemStatusId and item.barcode = :barcode and item.isDeleted = 0")
    String getItemStatusByBarcodeAndIsDeletedFalse(@Param("barcode") String barcode);
}