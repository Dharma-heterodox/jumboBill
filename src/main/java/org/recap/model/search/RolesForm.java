package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemalathas on 22/12/16.
 */
public class RolesForm {
    private String roleName;
    private String roleDescription;
    private String permissionNames;
    private boolean showResults = false;
    private boolean newRole = false;
    private String totalRecordCount="0";
    private String errorMessage;
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Integer totalPageCount = 0;
    private Integer afterDelPageNumber=0;
    private Integer afterDelPageSize =10;
    private Integer afterDelTotalPageCount=0;
    private String message;
    private String newRoleName;
    private String newRoleDescription;
    private String newPermissionNames;
    private String editRoleName;
    private String editRoleDescription;
    private String editPermissionNames;
    private List<String> editPermissionName = new ArrayList<>();
    private String roleNameForDelete;
    private String roleDescriptionForDelete;
    private String permissionNamesForDelete;
    private List<String> permissionNameList = new ArrayList<>();
    private List<String> selectedPermissionNames = new ArrayList<>();
    private Integer roleId;
    private List<RolesSearchResult> rolesSearchResults = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String permissionNames) {
        this.permissionNames = permissionNames;
    }

    public boolean isShowResults() {
        return showResults;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public String getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(String totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public List<RolesSearchResult> getRolesSearchResults() {
        return rolesSearchResults;
    }

    public void setRolesSearchResults(List<RolesSearchResult> rolesSearchResults) {
        this.rolesSearchResults = rolesSearchResults;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public boolean isNewRole() {
        return newRole;
    }

    public void setNewRole(boolean newRole) {
        this.newRole = newRole;
    }

    public String getMessage() {
        return message;
    }

    public String getNewRoleName() {
        return newRoleName;
    }

    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }

    public String getNewRoleDescription() {
        return newRoleDescription;
    }

    public void setNewRoleDescription(String newRoleDescription) {
        this.newRoleDescription = newRoleDescription;
    }

    public String getNewPermissionNames() {
        return newPermissionNames;
    }

    public void setNewPermissionNames(String newPermissionNames) {
        this.newPermissionNames = newPermissionNames;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getPermissionNameList() {
        return permissionNameList;
    }

    public void setPermissionNameList(List<String> permissionNameList) {
        this.permissionNameList = permissionNameList;
    }

    public List<String> getSelectedPermissionNames() {
        return selectedPermissionNames;
    }

    public void setSelectedPermissionNames(List<String> selectedPermissionNames) {
        this.selectedPermissionNames = selectedPermissionNames;
    }

    public String getEditRoleName() {
        return editRoleName;
    }

    public void setEditRoleName(String editRoleName) {
        this.editRoleName = editRoleName;
    }

    public String getEditRoleDescription() {
        return editRoleDescription;
    }

    public void setEditRoleDescription(String editRoleDescription) {
        this.editRoleDescription = editRoleDescription;
    }

    public String getEditPermissionNames() {
        return editPermissionNames;
    }

    public void setEditPermissionNames(String editPermissionNames) {
        this.editPermissionNames = editPermissionNames;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleNameForDelete() {
        return roleNameForDelete;
    }

    public void setRoleNameForDelete(String roleNameForDelete) {
        this.roleNameForDelete = roleNameForDelete;
    }

    public String getRoleDescriptionForDelete() {
        return roleDescriptionForDelete;
    }

    public void setRoleDescriptionForDelete(String roleDescriptionForDelete) {
        this.roleDescriptionForDelete = roleDescriptionForDelete;
    }

    public String getPermissionNamesForDelete() {
        return permissionNamesForDelete;
    }

    public void setPermissionNamesForDelete(String permissionNamesForDelete) {
        this.permissionNamesForDelete = permissionNamesForDelete;
    }

    public List<String> getEditPermissionName() {
        return editPermissionName;
    }

    public void setEditPermissionName(List<String> editPermissionName) {
        this.editPermissionName = editPermissionName;
    }

    public void reset(){
        this.errorMessage=null;
        this.message=null;
        this.totalRecordCount=String.valueOf(0);
        this.rolesSearchResults=new ArrayList<>();
        this.totalPageCount=0;

    }

    public void resetPageNumber() {
        this.pageNumber = 0;
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
}
