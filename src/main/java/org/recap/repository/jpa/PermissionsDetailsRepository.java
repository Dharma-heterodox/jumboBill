package org.recap.repository.jpa;

import org.recap.model.jpa.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created by dharmendrag on 13/12/16.
 */
public interface PermissionsDetailsRepository extends JpaRepository<PermissionEntity,Integer> {

    PermissionEntity findByPermissionName(String permissionName);

    @Query(value = "select permission from PermissionEntity permission where permission.permissionName = :permissionName")
    Page<PermissionEntity> findByPermissionName(Pageable pageable, @Param("permissionName") String permissionName);

}
