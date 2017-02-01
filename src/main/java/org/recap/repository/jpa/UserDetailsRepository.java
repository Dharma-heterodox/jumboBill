package org.recap.repository.jpa;

import org.recap.model.jpa.InstitutionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dharmendrag on 29/11/16.
 */
@Repository
public interface UserDetailsRepository extends JpaRepository<UsersEntity,Integer>,JpaSpecificationExecutor {



    UsersEntity findByLoginId(String loginId);

    UsersEntity findByLoginIdAndInstitutionEntity(String loginId, InstitutionEntity institutionId);

    UsersEntity findByUserId(Integer userId);

    @Query(value = "select userT.passwrd from user_master_t userT where userT.login_id=:loginId",nativeQuery = true)
    String validateUser(@Param("loginId") String loginId);

    @Query(value="select roleT.role_name from role_master_t roleT,user_master_t userT where userT.user_role_id=roleT.role_id",nativeQuery = true)
    RoleEntity userRole(@Param("loginId") String loginId);

    @Query(value="select users from UsersEntity users where users.loginId not in ('superadmin') ")
    Page<UsersEntity> findAll(Pageable pageable);

    Page<UsersEntity> findByInstitutionEntity(InstitutionEntity institutionId, Pageable pageable);

    Page<UsersEntity> findByLoginId(String loginId, Pageable pageable);

    Page<UsersEntity> findByLoginIdAndInstitutionEntity(String loginId, InstitutionEntity institutionId, Pageable pageable);


    UsersEntity findByLoginIdAndInstitutionId(String networkLoginId, Integer institutionId);

    Page<UsersEntity> findByEmailId(String userEmailId, Pageable pageable);

    Page<UsersEntity> findByEmailIdAndInstitutionEntity(String userEmailId, InstitutionEntity institutionEntity, Pageable pageable);

    Page<UsersEntity> findByLoginIdAndEmailId(String searchNetworkId, String userEmailId, Pageable pageable);

    Page<UsersEntity> findByLoginIdAndEmailIdAndInstitutionEntity(String searchNetworkId, String userEmailId, InstitutionEntity institutionEntity, Pageable pageable);
}
