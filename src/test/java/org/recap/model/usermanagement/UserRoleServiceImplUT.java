package org.recap.model.usermanagement;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.UsersEntity;
import org.recap.model.userManagement.UserRoleForm;
import org.recap.model.userManagement.UserRoleService;
import org.recap.repository.jpa.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by akulak on 24/1/17.
 */
public class UserRoleServiceImplUT extends BaseTestCase {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRoleService userRoleService;

    @Test
    public void searchUsers(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchUsers(userRoleForm, false);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchUsersForSuperAdmin(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchUsers(userRoleForm, true);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchByNetworkId(){
        UserRoleForm userRoleForm = getUserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        Page<UsersEntity> usersEntities = userRoleService.searchByNetworkId(userRoleForm, false);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchByNetworkIdForSuperAdmin(){
        UserRoleForm userRoleForm = getUserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        Page<UsersEntity> usersEntities = userRoleService.searchByNetworkId(userRoleForm, true);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchByUserEmailId(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchByUserEmailId(userRoleForm, false);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchByUserEmailIdForSuperAdmin(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchByUserEmailId(userRoleForm, true);
        assertNotNull(usersEntities);
    }

    @Test
    public void searchByNetworkIdAndUserEmailId(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchByNetworkIdAndUserEmailId(userRoleForm, false);
        assertNotNull(usersEntities);

    }

    @Test
    public void searchByNetworkIdAndUserEmailIdForSuperAdmin(){
        UserRoleForm userRoleForm = getUserRoleForm();
        Page<UsersEntity> usersEntities = userRoleService.searchByNetworkIdAndUserEmailId(userRoleForm, true);
        assertNotNull(usersEntities);
    }

    @Test
    public void saveNewUserToDB(){
        UserRoleForm userRoleForm = getUserRoleForm();
        UsersEntity usersEntity = userRoleService.saveNewUserToDB(userRoleForm);
        assertNotNull(usersEntity);
        assertEquals(userRoleForm.getNetworkLoginId(),usersEntity.getLoginId());
        assertEquals(userRoleForm.getUserDescription(),usersEntity.getUserDescription());
        assertEquals(userRoleForm.getEmailId(),usersEntity.getEmailId());
    }

    @Test
    public void saveEditedUserToDB(){
        UserRoleForm userRoleForm = getUserRoleForm();
        UsersEntity usersEntity = userRoleService.saveNewUserToDB(userRoleForm);
        assertNotNull(usersEntity);
        String networkLoginId = "testeditut";
        String userDescription = "testeditdesc";
        Integer institutionId = 1;
        String userEmailId = "testedit@mail.com";
        UsersEntity editedUserEntity = userRoleService.saveEditedUserToDB(usersEntity.getUserId(), networkLoginId, userDescription, institutionId, Arrays.asList(1), userEmailId,userRoleForm);
        assertNotNull(editedUserEntity);
        assertEquals(usersEntity.getLoginId(),editedUserEntity.getLoginId());
        assertEquals(usersEntity.getEmailId(),editedUserEntity.getEmailId());
        assertEquals(usersEntity.getUserDescription(),editedUserEntity.getUserDescription());
    }

    @Test
    public void getRoles(){
        List<Object> roles = userRoleService.getRoles(1);
        assertNotNull(roles);
        boolean superAdminRoleId = roles.contains(1);
        assertEquals(false,superAdminRoleId);
    }

    @Test
    public void getInstitutionsForSuperAdmin(){
        List<Object> institutions = userRoleService.getInstitutions(true, 1);
        assertNotNull(institutions);
        assertEquals(3,institutions.size());
    }

    @Test
    public void getInstitutionsForPulUser(){
        List<Object> institutions = userRoleService.getInstitutions(false, 1);
        assertNotNull(institutions);
        assertEquals(1,institutions.size());
    }


    @Test
    public void getInstitutionsForCulUser(){
        List<Object> institutions = userRoleService.getInstitutions(false, 2);
        assertNotNull(institutions);
        assertEquals(1,institutions.size());
    }

    @Test
    public void getInstitutionsForNyplUser(){
        List<Object> institutions = userRoleService.getInstitutions(false, 3);
        assertNotNull(institutions);
        assertEquals(1,institutions.size());
    }



    private UserRoleForm getUserRoleForm() {
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setNetworkLoginId("testUt");
        userRoleForm.setUserDescription("testdesc");
        userRoleForm.setEmailId("testUt@mail.com");
        userRoleForm.setInstitutionId(1);
        userRoleForm.setSelectedForCreate(Arrays.asList(1,2));
        userRoleForm.setPageSize(10);
        userRoleForm.setPageNumber(0);
        userRoleForm.setCreatedBy("test");
        userRoleForm.setLastUpdatedBy("test");
        return userRoleForm;
    }


}
