package org.recap.model.userManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dharmendrag on 23/12/16.
 */
public class UserRoleForm {

    private int userId;
    private int institutionId;
    private int editUserId;
    private int[] roleId;
    private int[] editRoleId;

    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Integer totalPageCount = 0;
    private Integer editInstitutionId;
    private Integer afterDelPageNumber=0;
    private Integer afterDelPageSize =10;
    private Integer afterDelTotalPageCount=0;

    private String searchNetworkId;
    private String networkLoginId;
    private String roleName;
    private String totalRecordsCount = "0";
    private String institutionName;
    private String message;
    private String errorMessage;
    private String errorMessageForEmail;
    private String editErromessage;
    private String sectionName;
    private String buttonName;
    private String editNetworkId;
    private String userDescriptionErrMsg;
    private String userDescription;
    private String editNetworkLoginId;
    private String editUserDescription;
    private String userEmailId;
    private String emailId;
    private String editEmailId;

    private boolean allowCreateEdit;
    private boolean isCreatedRequest;
    private boolean showPagination = false;
    private boolean showSearch = false;
    private boolean showErrorMessage = false;
    private boolean showCreateSuccess = false;
    private boolean showCreateError = false;
    private boolean showEditSuccess = false;
    private boolean showEditError = false;
    private boolean showCreateEmailError = false;
    private boolean deleteSuccessMsg = false;
    private boolean selected;
    private boolean submitted;
    private boolean showResults = false;
    private boolean deletedSuccessMsg = false;
    private boolean deleteErrorMsg= false;

    private List<Object> roles = new ArrayList<Object>();
    private List<Object> institutions = new ArrayList<Object>();
    private List<Integer> showSelectedForCreate = new ArrayList<>();
    private List<Integer> selectedForCreate = new ArrayList<>();
    private List<Integer> editSelectedForCreate = new ArrayList<>();

    private String createdBy;
    private String lastUpdatedBy;

    public boolean isShowCreateError() {
        return showCreateError;
    }

    public void setShowCreateError(boolean showCreateError) {
        this.showCreateError = showCreateError;
    }

    public boolean isShowCreateSuccess() {
        return showCreateSuccess;
    }

    public void setShowCreateSuccess(boolean showCreateSuccess) {
        this.showCreateSuccess = showCreateSuccess;
    }

    public boolean isShowErrorMessage() {
        return showErrorMessage;
    }

    public void setShowErrorMessage(boolean showErrorMessage) {
        this.showErrorMessage = showErrorMessage;
    }

    public boolean isCreatedRequest() {
        return isCreatedRequest;
    }

    public void setCreatedRequest(boolean createdRequest) {
        isCreatedRequest = createdRequest;
    }

    public String getSearchNetworkId() {
        return searchNetworkId;
    }

    public void setSearchNetworkId(String searchNetworkId) {
        this.searchNetworkId = searchNetworkId;
    }

    public String getEditNetworkId() {
        return editNetworkId;
    }

    public void setEditNetworkId(String editNetworkId) {
        this.editNetworkId = editNetworkId;
    }

    public int[] getEditRoleId() {
        return editRoleId;
    }

    public void setEditRoleId(int[] editRoleId) {
        this.editRoleId = editRoleId;
    }


    public List<Object> getRoles() {
        return roles;
    }

    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    public List<Object> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Object> institutions) {
        this.institutions = institutions;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public boolean isAllowCreateEdit() {
        return allowCreateEdit;
    }

    public void setAllowCreateEdit(boolean allowCreateEdit) {
        this.allowCreateEdit = allowCreateEdit;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isShowResults() {
        return showResults;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    private List<UserRoleForm> userRoleFormList = new ArrayList<UserRoleForm>();

    public List<UserRoleForm> getUserRoleFormList() {
        return userRoleFormList;
    }

    public void setUserRoleFormList(List<UserRoleForm> userRoleFormList) {
        this.userRoleFormList = userRoleFormList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(int editUserId) {
        this.editUserId = editUserId;
    }

    public String getNetworkLoginId() {
        return networkLoginId;
    }

    public void setNetworkLoginId(String networkLoginId) {
        this.networkLoginId = networkLoginId;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int[] getRoleId() {
        return roleId;
    }

    public void setRoleId(int[] roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(String totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void resetPageNumber() {
        this.pageNumber = 0;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserDescriptionErrMsg() {
        return userDescriptionErrMsg;
    }

    public void setUserDescriptionErrMsg(String userDescriptionErrMsg) {
        this.userDescriptionErrMsg = userDescriptionErrMsg;
    }

    public boolean isShowPagination() {
        return showPagination;
    }

    public void setShowPagination(boolean showPagination) {
        this.showPagination = showPagination;
    }

    public boolean isShowSearch() {
        return showSearch;
    }

    public void setShowSearch(boolean showSearch) {
        this.showSearch = showSearch;
    }

    public List<Integer> getShowSelectedForCreate() {
        return showSelectedForCreate;
    }

    public void setShowSelectedForCreate(List<Integer> showSelectedForCreate) {
        this.showSelectedForCreate = showSelectedForCreate;
    }

    public List<Integer> getSelectedForCreate() {
        return selectedForCreate;
    }

    public void setSelectedForCreate(List<Integer> selectedForCreate) {
        this.selectedForCreate = selectedForCreate;
    }

    public boolean isShowEditSuccess() {
        return showEditSuccess;
    }

    public void setShowEditSuccess(boolean showEditSuccess) {
        this.showEditSuccess = showEditSuccess;
    }

    public boolean isShowEditError() {
        return showEditError;
    }

    public void setShowEditError(boolean showEditError) {
        this.showEditError = showEditError;
    }

    public String getEditNetworkLoginId() {
        return editNetworkLoginId;
    }

    public void setEditNetworkLoginId(String editNetworkLoginId) {
        this.editNetworkLoginId = editNetworkLoginId;
    }

    public void setEditInstitutionId(Integer editInstitutionId) {
        this.editInstitutionId = editInstitutionId;
    }

    public List<Integer> getEditSelectedForCreate() {
        return editSelectedForCreate;
    }

    public void setEditSelectedForCreate(List<Integer> editSelectedForCreate) {
        this.editSelectedForCreate = editSelectedForCreate;
    }

    public String getEditUserDescription() {
        return editUserDescription;
    }

    public void setEditUserDescription(String editUserDescription) {
        this.editUserDescription = editUserDescription;
    }

    public Integer getEditInstitutionId() {
        return editInstitutionId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEditEmailId() {
        return editEmailId;
    }

    public void setEditEmailId(String editEmailId) {
        this.editEmailId = editEmailId;
    }

    public boolean isDeleteSuccessMsg() {
        return deleteSuccessMsg;
    }

    public void setDeleteSuccessMsg(boolean deleteSuccessMsg) {
        this.deleteSuccessMsg = deleteSuccessMsg;
    }

    public boolean isDeletedSuccessMsg() {
        return deletedSuccessMsg;
    }

    public void setDeletedSuccessMsg(boolean deletedSuccessMsg) {
        this.deletedSuccessMsg = deletedSuccessMsg;
    }

    public boolean isDeleteErrorMsg() {
        return deleteErrorMsg;
    }

    public void setDeleteErrorMsg(boolean deleteErrorMsg) {
        this.deleteErrorMsg = deleteErrorMsg;
    }

    public Integer getAfterDelPageNumber() {
        return afterDelPageNumber;
    }

    public void setAfterDelPageNumber(Integer afterDelPageNumber) {
        this.afterDelPageNumber = afterDelPageNumber;
    }

    public Integer getAfterDelPageSize() {
        return afterDelPageSize;
    }

    public void setAfterDelPageSize(Integer afterDelPageSize) {
        this.afterDelPageSize = afterDelPageSize;
    }

    public Integer getAfterDelTotalPageCount() {
        return afterDelTotalPageCount;
    }

    public void setAfterDelTotalPageCount(Integer afterDelTotalPageCount) {
        this.afterDelTotalPageCount = afterDelTotalPageCount;
    }

    public String getEditErromessage() {
        return editErromessage;
    }

    public void setEditErromessage(String editErromessage) {
        this.editErromessage = editErromessage;
    }

    public String getErrorMessageForEmail() {
        return errorMessageForEmail;
    }

    public void setErrorMessageForEmail(String errorMessageForEmail) {
        this.errorMessageForEmail = errorMessageForEmail;
    }

    public boolean isShowCreateEmailError() {
        return showCreateEmailError;
    }

    public void setShowCreateEmailError(boolean showCreateEmailError) {
        this.showCreateEmailError = showCreateEmailError;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}