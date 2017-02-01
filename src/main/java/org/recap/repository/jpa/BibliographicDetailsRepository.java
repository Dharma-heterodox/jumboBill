package org.recap.repository.jpa;

import org.recap.model.jpa.BibliographicEntity;
import org.recap.model.jpa.BibliographicPK;
import org.recap.model.jpa.HoldingsEntity;
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
 * Created by pvsubrah on 6/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "bibliographic", path = "bibliographic")
public interface BibliographicDetailsRepository extends JpaRepository<BibliographicEntity, BibliographicPK> {

    Long countByIsDeletedFalse();

    Long countByOwningInstitutionId(Integer owningInstitutionId);

    Long countByLastUpdatedDateAfter(Date createdDate);

    Long countByOwningInstitutionIdAndLastUpdatedDateAfter(Integer owningInstitutionId, Date createdDate);

    Page<BibliographicEntity> findByLastUpdatedDateAfter(Pageable pageable, Date createdDate);

    Page<BibliographicEntity> findByOwningInstitutionIdAndLastUpdatedDateAfter(Pageable pageable, Integer institutionId, Date createdDate);

    Long countByOwningInstitutionIdAndIsDeletedFalse(Integer owningInstitutionId);

    Page<BibliographicEntity> findAllByIsDeletedFalse(Pageable pageable);

    Page<BibliographicEntity> findByOwningInstitutionIdAndIsDeletedFalse(Pageable pageable, Integer institutionId);

    Page<BibliographicEntity> findByOwningInstitutionId(Pageable pageable, Integer institutionId);

    BibliographicEntity findByOwningInstitutionIdAndOwningInstitutionBibIdAndIsDeletedFalse(Integer owningInstitutionId, String owningInstitutionBibId);

    BibliographicEntity findByBibliographicId(@Param("bibliographicId") Integer bibliographicId);

    BibliographicEntity findByBibliographicIdAndIsDeletedFalse(@Param("bibliographicId") Integer bibliographicId);

    BibliographicEntity findByOwningInstitutionIdAndOwningInstitutionBibId(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionBibId") String owningInstitutionBibId);

    List<BibliographicEntity> findByOwningInstitutionBibId(String owningInstitutionBibId);

    @Query(value = "select count(*) from bibliographic_t bib, institution_t inst where bib.OWNING_INST_ID = inst.INSTITUTION_ID AND inst.INSTITUTION_CODE=?1 limit 1", nativeQuery = true)
    Long countByOwningInstitutionCodeAndIsDeletedFalse(String institutionCode);

    List<HoldingsEntity> getNonDeletedHoldingsEntities(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionBibId") String owningInstitutionBibId);

    List<ItemEntity> getNonDeletedItemEntities(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionBibId") String owningInstitutionBibId);

    @Query(value = "SELECT COUNT(*) FROM ITEM_T, BIBLIOGRAPHIC_ITEM_T WHERE BIBLIOGRAPHIC_ITEM_T.ITEM_INST_ID = ITEM_T.OWNING_INST_ID " +
            "AND BIBLIOGRAPHIC_ITEM_T.OWNING_INST_ITEM_ID = ITEM_T.OWNING_INST_ITEM_ID AND ITEM_T.IS_DELETED = 0 AND " +
            "BIBLIOGRAPHIC_ITEM_T.OWNING_INST_BIB_ID = :owningInstitutionBibId AND BIBLIOGRAPHIC_ITEM_T.BIB_INST_ID = :owningInstitutionId", nativeQuery = true)
    Long getNonDeletedItemsCount(@Param("owningInstitutionId") Integer owningInstitutionId, @Param("owningInstitutionBibId") String owningInstitutionBibId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE BibliographicEntity bib SET bib.isDeleted = true, bib.lastUpdatedBy = :lastUpdatedBy, bib.lastUpdatedDate = :lastUpdatedDate WHERE bib.bibliographicId IN :bibliographicIds")
    int markBibsAsDeleted(@Param("bibliographicIds") List<Integer> bibliographicIds, @Param("lastUpdatedBy") String lastUpdatedBy, @Param("lastUpdatedDate") Date lastUpdatedDate);

}