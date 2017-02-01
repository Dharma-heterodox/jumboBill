package org.recap.repository.jpa;

import org.recap.model.jpa.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by dharmendrag on 13/12/16.
 */
public interface RolesDetailsRepositorty extends JpaRepository<RoleEntity, Integer> {

    Page<RoleEntity> findByRoleName(String roleName, Pageable pageable);

    RoleEntity findByRoleName(String roleName);

    @Query(value = "select roles from RoleEntity roles where roles.roleName not in ('SuperAdmin')")
    Page<RoleEntity> getRolesWithoutSuperAdmin(Pageable pageable);

    @Query(value = "select roles from RoleEntity roles where roles.roleName = :roleName")
    Page<RoleEntity> findByRoleName(Pageable pageable, @Param("roleName") String roleName);

    @Query(value = "select distinct(ROLE_ID) from role_permission_t where PERMISSION_ID = :permissionID",nativeQuery = true)
    List<Integer> getRoleIDforPermissionName(@Param("permissionID") Integer permissionID);

    @Query(value="select roles from RoleEntity roles where roles.roleId In(:roleID) and roles.roleName not in ('SuperAdmin')")
    Page<RoleEntity> findByRoleID(Pageable pageable, @Param("roleID") List<Integer> roleID);

    List<RoleEntity> findByRoleIdIn(List<Integer> roleIds);

    RoleEntity findByRoleId(Integer roleId);

}
