package org.recap.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.mockito.Mock;
import org.recap.BaseTestCase;
import org.recap.model.userManagement.UserForm;
import org.recap.model.userManagement.UserRoleForm;
import org.recap.model.userManagement.UserRoleService;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.RolesDetailsRepositorty;
import org.recap.repository.jpa.UserDetailsRepository;
import org.recap.security.UserManagement;
import org.recap.util.UserAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by akulak on 24/1/17.
 */
public class UserRoleControllerUT extends BaseTestCase {

    @Autowired
    UserRoleController userRoleController;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @Autowired
    InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Autowired
    LoginController loginController;

    @Test
    public void showUserRoles() throws Exception{
        when(request.getSession()).thenReturn(session);
        usersSessionAttributes();
        String view = userRoleController.showUserRoles(model, request);
        assertNotNull(view);
        assertEquals("searchRecords",view);
    }

    @Test
    public void searchUserRole() throws Exception{
        UserRoleForm userRoleForm = new UserRoleForm();
        usersSessionAttributes();
        userRoleForm.setSearchNetworkId("smith");
        ModelAndView modelAndView = userRoleController.searchUserRole(userRoleForm, model, request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch :: #request-result-table",modelAndView.getViewName());
    }

    @Test
    public void deleteUserRole() throws Exception{
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        usersSessionAttributes();
        ModelAndView modelAndView = userRoleController.deleteUserRole(userRoleForm.getSearchNetworkId(),3,request,10,1,2);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch",modelAndView.getViewName());
    }

    @Test
    public void deleteUser()throws Exception{
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        usersSessionAttributes();
        ModelAndView modelAndView = userRoleController.deleteUser(userRoleForm,model,3,userRoleForm.getSearchNetworkId(),10,1,2,request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch",modelAndView.getViewName());
    }

    @Test
    public void searchFirstPage()throws Exception{
        usersSessionAttributes();
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        userRoleForm.setPageSize(10);
        userRoleForm.setPageNumber(1);
        ModelAndView modelAndView = userRoleController.searchFirstPage(userRoleForm, model, request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch :: #request-result-table",modelAndView.getViewName());
    }

    @Test
    public void searchNextPage()throws Exception{
        usersSessionAttributes();
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        userRoleForm.setPageSize(10);
        userRoleForm.setPageNumber(1);
        ModelAndView modelAndView = userRoleController.searchNextPage(userRoleForm, model, request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch :: #request-result-table",modelAndView.getViewName());
    }


    @Test
    public void searchPreviousPage()throws Exception{
        usersSessionAttributes();
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        userRoleForm.setPageSize(10);
        userRoleForm.setPageNumber(1);
        ModelAndView modelAndView = userRoleController.searchPreviousPage(userRoleForm,model, request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch :: #request-result-table",modelAndView.getViewName());
    }

    @Test
    public void searchLastPage()throws Exception{
        usersSessionAttributes();
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setSearchNetworkId("smith");
        userRoleForm.setPageSize(10);
        userRoleForm.setPageNumber(1);
        ModelAndView modelAndView = userRoleController.searchLastPage(userRoleForm, model, request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch :: #request-result-table",modelAndView.getViewName());
    }

    @Test
    public void createUserRequest()throws Exception{
        usersSessionAttributes();
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setNetworkLoginId("test");
        userRoleForm.setEmailId("test@gmail.com");
        userRoleForm.setInstitutionId(2);
        userRoleForm.setUserDescription("testdescription");
        List<Integer> role = new ArrayList<>();
        role.add(2);
        userRoleForm.setSelectedForCreate(role);
        ModelAndView modelAndView = userRoleController.createUserRequest(userRoleForm,request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch",modelAndView.getViewName());
    }

    @Test
    public void editUser()throws Exception{
        usersSessionAttributes();
        ModelAndView modelAndView = userRoleController.editUser(3, "smith", request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch",modelAndView.getViewName());
    }

    @Test
    public void saveEditUserDetails()throws Exception{
        usersSessionAttributes();
        String[] roleIds = {"2","3"};
        when(request.getParameterValues("roleIds[]")).thenReturn(roleIds);
        ModelAndView modelAndView = userRoleController.saveEditUserDetails(3, "test", "test description", 2, "test@mail.com", request);
        assertNotNull(modelAndView);
        assertEquals("userRolesSearch",modelAndView.getViewName());

    }

    private void usersSessionAttributes() throws Exception {
        when(request.getSession()).thenReturn(session);
        UserForm userForm = new UserForm();
        userForm.setUsername("kholi");
        userForm.setInstitution(3);
        userForm.setPassword("12345");
        UsernamePasswordToken token=new UsernamePasswordToken(userForm.getUsername()+ UserManagement.TOKEN_SPLITER.getValue()+userForm.getInstitution(),userForm.getPassword(),true);
        userAuthUtil.doAuthentication(token);
        when(session.getAttribute(UserManagement.USER_TOKEN)).thenReturn(token);
        when(session.getAttribute(UserManagement.USER_ID)).thenReturn(3);
        when(session.getAttribute(UserManagement.SUPER_ADMIN_USER)).thenReturn(false);
        when(session.getAttribute(UserManagement.BARCODE_RESTRICTED_PRIVILEGE)).thenReturn(false);
        when(session.getAttribute(UserManagement.USER_INSTITUTION)).thenReturn(1);
        when(session.getAttribute(UserManagement.REQUEST_ALL_PRIVILEGE)).thenReturn(false);
        userAuthUtil.getUserDetails(session,UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
    }

}
