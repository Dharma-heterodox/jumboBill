package org.recap.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.recap.RecapConstants;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.model.userManagement.UserDetailsForm;
import org.recap.model.userManagement.UserRoleForm;
import org.recap.model.userManagement.UserRoleService;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.RolesDetailsRepositorty;
import org.recap.repository.jpa.UserDetailsRepository;
import org.recap.security.UserManagement;
import org.recap.util.UserAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dharmendrag on 23/12/16.
 */
@Controller
public class UserRoleController {

    Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @Autowired
    InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @RequestMapping(value = "/userRoles")
    public String showUserRoles(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
            logger.info("Users Tab Clicked");
            UserRoleForm userRoleForm = new UserRoleForm();
            UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(session, UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
            List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
            List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
            userRoleForm.setRoles(roles);
            userRoleForm.setInstitutions(institutions);
            userRoleForm.setAllowCreateEdit(true);
            model.addAttribute("userRoleForm", userRoleForm);
            model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
            return "searchRecords";
        } else {
            return UserManagement.unAuthorizedUser(session,"Users",logger);
        }

    }

    //Search
    @ResponseBody
    @RequestMapping(value = "/userRoles/searchUsers", method = RequestMethod.POST)
    public ModelAndView searchUserRole(@Valid @ModelAttribute("userRoleForm") UserRoleForm userRoleForm, Model model, HttpServletRequest request) {
        logger.info("Users - Search button Clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        try {
            priorSearch(userRoleForm, request);
            userRoleForm.setShowPagination(true);
            model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("userRolesSearch :: #request-result-table", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }


    //DeleteUser Screen
    @ResponseBody
    @RequestMapping(value = "/userRoles/deleteUser", method = RequestMethod.POST)
    public ModelAndView deleteUserRole(String networkLoginId, Integer userId, HttpServletRequest request,Integer pagesize,Integer pageNumber,Integer totalPageCount) {
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(request.getSession(), UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        logger.info("User - Delete User clicked");
        logger.info("User Id  " + userId);
        logger.info("NetworkLoginId  " + networkLoginId);
        UsersEntity usersEntity = userDetailsRepository.findByLoginId(networkLoginId);
        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setAfterDelPageSize(pagesize);
        userRoleForm.setAfterDelPageNumber(pageNumber);
        userRoleForm.setAfterDelTotalPageCount(totalPageCount);
        List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
        List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
        userRoleForm.setEditNetworkLoginId(usersEntity.getLoginId());
        userRoleForm.setInstitutions(institutions);
        userRoleForm.setRoles(roles);
        userRoleForm.setEditUserDescription(usersEntity.getUserDescription());
        userRoleForm.setEditUserId(userId);
        userRoleForm.setUserId(userId);
        userRoleForm.setEmailId(usersEntity.getEmailId());
        userRoleForm.setEditEmailId(usersEntity.getEmailId());
        List<RoleEntity> roleEntityList = usersEntity.getUserRole();
        List<Integer> roleIds = new ArrayList<>();
        if (roleEntityList != null) {
            for (RoleEntity roleEntity : roleEntityList) {
                roleIds.add(roleEntity.getRoleId());

            }
        }
        userRoleForm.setEditSelectedForCreate(roleIds);
        userRoleForm.setShowSelectedForCreate(userRoleForm.getEditSelectedForCreate());
        userRoleForm.setEditInstitutionId(usersEntity.getInstitutionId());
        return new ModelAndView("userRolesSearch", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }


    //DeleteUser On Confirm
    @ResponseBody
    @RequestMapping(value = "/userRoles/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@Valid @ModelAttribute("userRoleForm") UserRoleForm userRoleForm, Model model, Integer userId, String networkLoginId, Integer pageNumber, Integer totalPageCount, Integer pageSize, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(userId);
        try {
            userDetailsRepository.delete(usersEntity);
            userRoleForm.setDeletedSuccessMsg(true);
            userRoleForm.setMessage(networkLoginId + RecapConstants.USER_DELETED_SUCCESS_MESSAGE);
            priorSearch(userRoleForm, request);
            userRoleForm.setAfterDelPageNumber(pageNumber);
            userRoleForm.setAfterDelTotalPageCount(totalPageCount);
            userRoleForm.setAfterDelPageSize(pageSize);
            userRoleForm.setShowPagination(true);
            userRoleForm.setShowResults(true);
            model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView("userRolesSearch", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles/first", method = RequestMethod.POST)
    public ModelAndView searchFirstPage(@ModelAttribute("userForm") UserRoleForm userRoleForm, Model model, HttpServletRequest request) {
        logger.info("Users - Search First Page button Clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        userRoleForm.resetPageNumber();
        priorSearch(userRoleForm, request);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        return new ModelAndView("userRolesSearch :: #request-result-table", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles/next", method = RequestMethod.POST)
    public ModelAndView searchNextPage(@ModelAttribute("userForm") UserRoleForm userRoleForm, Model model, HttpServletRequest request) {
        logger.info("Users - Search Next Page button Clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        priorSearch(userRoleForm, request);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        return new ModelAndView("userRolesSearch :: #request-result-table", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles/previous", method = RequestMethod.POST)
    public ModelAndView searchPreviousPage(@ModelAttribute("userForm") UserRoleForm userRoleForm, Model model, HttpServletRequest request) {
        logger.info("Users - Search Previous Page button Clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        priorSearch(userRoleForm, request);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        return new ModelAndView("userRolesSearch :: #request-result-table", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles/last", method = RequestMethod.POST)
    public ModelAndView searchLastPage(@ModelAttribute("userForm") UserRoleForm userRoleForm, Model model, HttpServletRequest request) {
        logger.info("Users - Search Last Page button Clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        priorSearch(userRoleForm, request);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.USER_ROLES);
        return new ModelAndView("userRolesSearch :: #request-result-table", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userRoles/createUser", method = RequestMethod.POST)
    public ModelAndView createUserRequest(@ModelAttribute("userRoleForm") UserRoleForm userRoleForm, HttpServletRequest request) {
        logger.info("User - Create Request clicked");
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(request.getSession(), UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
        List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
        userRoleForm.setRoles(roles);
        userRoleForm.setInstitutions(institutions);
        Object userName = session.getAttribute(UserManagement.USER_NAME);
        userRoleForm.setCreatedBy(String.valueOf(userName));
        UsersEntity usersEntity = userRoleService.saveNewUserToDB(userRoleForm);
        if (usersEntity != null) {
            userRoleForm.setUserDescription(userRoleForm.getUserDescription());
            userRoleForm.setShowCreateSuccess(true);
            userRoleForm.setAllowCreateEdit(true);
            userRoleForm.setUserDescription(userRoleForm.getUserDescription());
            userRoleForm.setInstitutionId(userRoleForm.getInstitutionId());
            userRoleForm.setShowSelectedForCreate(userRoleForm.getSelectedForCreate());
        }
        return new ModelAndView("userRolesSearch", "userRoleForm", userRoleForm);
        } else {
        return new ModelAndView("login");
        }
    }

    //Edit User
    @ResponseBody
    @RequestMapping(value = "/userRoles/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("userId") Integer userId, @ModelAttribute("networkLoginId") String networkLoginId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(request.getSession(), UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        logger.info("User - editUser clicked");
        logger.info("userid  " + userId);
        logger.info("networkLoginId  " + networkLoginId);
        UsersEntity usersEntity = userDetailsRepository.findByLoginId(networkLoginId);
        UserRoleForm userRoleForm = new UserRoleForm();
        List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
        List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
        userRoleForm.setEditNetworkLoginId(usersEntity.getLoginId());
        userRoleForm.setInstitutions(institutions);
        userRoleForm.setRoles(roles);
        userRoleForm.setEditUserDescription(usersEntity.getUserDescription());
        userRoleForm.setEditUserId(userId);
        userRoleForm.setUserId(userId);
        userRoleForm.setEmailId(usersEntity.getEmailId());
        userRoleForm.setEditEmailId(usersEntity.getEmailId());
        List<RoleEntity> roleEntityList = usersEntity.getUserRole();
        List<Integer> roleIds = new ArrayList<>();
        if (roleEntityList != null) {
            for (RoleEntity roleEntity : roleEntityList) {
                roleIds.add(roleEntity.getRoleId());

            }
        }
        userRoleForm.setEditSelectedForCreate(roleIds);
        userRoleForm.setShowSelectedForCreate(userRoleForm.getEditSelectedForCreate());
        userRoleForm.setEditInstitutionId(usersEntity.getInstitutionId());
        return new ModelAndView("userRolesSearch", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    //Save Edited User
    @ResponseBody
    @RequestMapping(value = "/userRoles/saveEditUserDetails", method = RequestMethod.POST)
    public ModelAndView saveEditUserDetails(@ModelAttribute("userId") Integer userId, @ModelAttribute("networkLoginId") String networkLoginId,
                                            @ModelAttribute("userDescription") String userDescription,
                                            @ModelAttribute("institutionId") Integer institutionId,
                                            @ModelAttribute("userEmailId") String userEmailId,
                                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean authenticated = userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_USER_ROLE_URL, (UsernamePasswordToken) session.getAttribute(UserManagement.USER_TOKEN));
        if (authenticated) {
        UserRoleForm userRoleForm = new UserRoleForm();
        UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(request.getSession(), UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        List<Integer> roleIds = new ArrayList<>();
        String[] parameterValues = request.getParameterValues("roleIds[]");
        for (String parameterValue : parameterValues) {
            roleIds.add(Integer.valueOf(parameterValue));
        }
        userRoleForm.setMessage(networkLoginId + RecapConstants.EDITED_AND_SAVED);
        List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
        List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
        userRoleForm.setInstitutions(institutions);
        userRoleForm.setRoles(roles);
        userRoleForm.setUserId(userId);
        Object userName = session.getAttribute(UserManagement.USER_NAME);
        userRoleForm.setLastUpdatedBy(String.valueOf(userName));
        UsersEntity usersEntity = userRoleService.saveEditedUserToDB(userId, networkLoginId, userDescription, institutionId, roleIds, userEmailId,userRoleForm);
        if (usersEntity != null) {
            userRoleForm.setShowEditSuccess(true);
            userRoleForm.setEditNetworkLoginId(usersEntity.getLoginId());
            userRoleForm.setEditUserDescription(usersEntity.getUserDescription());
            userRoleForm.setUserId(userRoleForm.getUserId());
            userRoleForm.setEditUserId(userRoleForm.getUserId());
            List<RoleEntity> roleEntityList = usersEntity.getUserRole();
            List<Integer> roleIdss = new ArrayList<>();
            if (roleEntityList != null) {
                for (RoleEntity roleEntity : roleEntityList) {
                    roleIdss.add(roleEntity.getRoleId());
                }
            }
            userRoleForm.setEditSelectedForCreate(roleIdss);
            userRoleForm.setShowSelectedForCreate(userRoleForm.getEditSelectedForCreate());
            userRoleForm.setEditInstitutionId(usersEntity.getInstitutionId());
            userRoleForm.setEditEmailId(usersEntity.getEmailId());
        } else {
            userRoleForm.setShowEditError(true);
            userRoleForm.setEditErromessage(RecapConstants.EMAILID_EXISTS);
            userRoleForm.setEditNetworkLoginId(networkLoginId);
            userRoleForm.setEditUserDescription(userDescription);
            userRoleForm.setEditSelectedForCreate(roleIds);
            userRoleForm.setShowSelectedForCreate(userRoleForm.getEditSelectedForCreate());
            userRoleForm.setEditInstitutionId(institutionId);
            userRoleForm.setEditEmailId(userEmailId);
        }

        return new ModelAndView("userRolesSearch", "userRoleForm", userRoleForm);
        } else {
            return new ModelAndView("login");
        }
    }

    private void priorSearch(UserRoleForm userRoleForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(UserManagement.USER_ID);
        UserDetailsForm userDetailsForm = userAuthUtil.getUserDetails(session, UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        List<Object> roles = userRoleService.getRoles(UserManagement.SUPER_ADMIN.getIntegerValues());
        List<Object> institutions = userRoleService.getInstitutions(userDetailsForm.isSuperAdmin(), userDetailsForm.getLoginInstitutionId());
        userRoleForm.setUserId(userId);
        userRoleForm.setInstitutionId(userDetailsForm.getLoginInstitutionId());
        userRoleForm.setRoles(roles);
        userRoleForm.setInstitutions(institutions);
        userRoleForm.setAllowCreateEdit(true);
        userRoleForm.setSubmitted(true);

        searchAndSetResult(userRoleForm, userDetailsForm.isSuperAdmin(), userId);
    }

    private void searchAndSetResult(UserRoleForm userRoleForm, boolean superAdmin, Integer userId) {
        if (StringUtils.isBlank(userRoleForm.getSearchNetworkId()) && StringUtils.isBlank(userRoleForm.getUserEmailId())) {
            logger.debug("Search All Users");
            Page<UsersEntity> usersEntities = userRoleService.searchUsers(userRoleForm, superAdmin);
            userRoleForm.setUserRoleFormList(setFormValues(usersEntities.getContent(), userId));
            userRoleForm.setShowResults(true);
            if(superAdmin){
                userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements()));
            }else{
                userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements() - 1));
            }
            userRoleForm.setTotalPageCount(usersEntities.getTotalPages());
        } else if (StringUtils.isNotBlank(userRoleForm.getSearchNetworkId()) && StringUtils.isBlank(userRoleForm.getUserEmailId())) {
            logger.debug("Search Users By NetworkId :" + userRoleForm.getSearchNetworkId());
            Page<UsersEntity> usersEntities = userRoleService.searchByNetworkId(userRoleForm, superAdmin);
            List<UsersEntity> userEntity = usersEntities.getContent();
            if (userEntity != null && userEntity.size() > 0) {
                userRoleForm.setUserRoleFormList(setFormValues(usersEntities.getContent(), userId));
                userRoleForm.setShowResults(true);
                if(superAdmin){
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements()));
                }else{
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements() - 1));
                }
                userRoleForm.setTotalPageCount(usersEntities.getTotalPages());
            } else {
                userRoleForm.setMessage(RecapConstants.NETWORK_LOGIN_ID_DOES_NOT_EXIST);
                userRoleForm.setShowErrorMessage(true);
                userRoleForm.setShowResults(false);
            }
        } else if (StringUtils.isBlank(userRoleForm.getSearchNetworkId()) && StringUtils.isNotBlank(userRoleForm.getUserEmailId())) {
            logger.debug("Search Users by Email Id:" + userRoleForm.getUserEmailId());
            Page<UsersEntity> usersEntities = userRoleService.searchByUserEmailId(userRoleForm, superAdmin);
            List<UsersEntity> userEntity = usersEntities.getContent();
            if (userEntity != null && userEntity.size() > 0) {
                userRoleForm.setUserRoleFormList(setFormValues(usersEntities.getContent(), userId));
                userRoleForm.setShowResults(true);
                if(superAdmin){
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements()));
                }else{
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements() - 1));
                }
                userRoleForm.setTotalPageCount(usersEntities.getTotalPages());
            } else {
                userRoleForm.setMessage(RecapConstants.EMAILID_ID_DOES_NOT_EXIST);
                userRoleForm.setShowErrorMessage(true);
                userRoleForm.setShowResults(false);
            }
        } else if (StringUtils.isNotBlank(userRoleForm.getSearchNetworkId()) && StringUtils.isNotBlank(userRoleForm.getUserEmailId())) {
            logger.debug("Search Users by Network Id : " + userRoleForm.getSearchNetworkId() + " and Email Id : " + userRoleForm.getUserEmailId());
            Page<UsersEntity> usersEntities = userRoleService.searchByNetworkIdAndUserEmailId(userRoleForm, superAdmin);
            List<UsersEntity> userEntity = usersEntities.getContent();
            if (userEntity != null && userEntity.size() > 0) {
                userRoleForm.setUserRoleFormList(setFormValues(usersEntities.getContent(), userId));
                userRoleForm.setShowResults(true);
                if(superAdmin){
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements()));
                }else{
                    userRoleForm.setTotalRecordsCount(String.valueOf(usersEntities.getTotalElements() - 1));
                }
                userRoleForm.setTotalPageCount(usersEntities.getTotalPages());
            } else {
                userRoleForm.setMessage(RecapConstants.NETWORK_LOGIN_ID_AND_EMAILID_ID_DOES_NOT_EXIST);
                userRoleForm.setShowErrorMessage(true);
                userRoleForm.setShowResults(false);
            }
        } else {
            userRoleForm.setShowResults(false);
        }
    }

    private List<UserRoleForm> setFormValues(List<UsersEntity> usersEntities, Integer userId) {
        List<UserRoleForm> userRoleFormList = new ArrayList<UserRoleForm>();
        appendValues(usersEntities, userRoleFormList, userId);
        return userRoleFormList;
    }

    private List<UserRoleForm> setValuesFromRole(List<RoleEntity> roleEntities, Integer userId, Integer loginInstitutionId) {
        List<UserRoleForm> userRoleFormList = new ArrayList<UserRoleForm>();
        for (RoleEntity rolesEntity : roleEntities) {
            appendValues(rolesEntity.getUsers(), userRoleFormList, userId);
        }
        return userRoleFormList;
    }

    private void appendValues(Collection<UsersEntity> usersEntities, List<UserRoleForm> userRoleFormList, Integer userId) {
        for (UsersEntity usersEntity : usersEntities) {
            InstitutionEntity institutionEntity = usersEntity.getInstitutionEntity();
            if (!userId.equals(usersEntity.getUserId()) && !usersEntity.getUserId().equals(UserManagement.SUPER_ADMIN.getIntegerValues())) {
                UserRoleForm userRoleDeatailsForm = new UserRoleForm();
                StringBuffer rolesBuffer = new StringBuffer();
                userRoleDeatailsForm.setUserId(usersEntity.getUserId());
                userRoleDeatailsForm.setInstitutionId(institutionEntity.getInstitutionId());
                userRoleDeatailsForm.setInstitutionName(institutionEntity.getInstitutionName());
                userRoleDeatailsForm.setNetworkLoginId(usersEntity.getLoginId());
                for (RoleEntity roleEntity : usersEntity.getUserRole()) {
                    rolesBuffer.append(roleEntity.getRoleName() + ",");
                }
                userRoleDeatailsForm.setRoleName(roles(rolesBuffer.toString(), ","));
                userRoleFormList.add(userRoleDeatailsForm);//Added all user's details
            }
        }
    }

    private String roles(String rolesBuffer, String seperator) {
        if (rolesBuffer != null && rolesBuffer.endsWith(seperator)) {
            return rolesBuffer.substring(0, rolesBuffer.length() - 1);
        }
        return null;
    }
}
