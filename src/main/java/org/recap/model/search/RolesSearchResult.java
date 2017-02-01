package org.recap.model.search;

/**
 * Created by hemalathas on 22/12/16.
 */
public class RolesSearchResult {

    private String rolesName;
    private String rolesDescription;
    private String permissionName;
    private Integer roleId;

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesDescription() {
        return rolesDescription;
    }

    public void setRolesDescription(String rolesDescription) {
        this.rolesDescription = rolesDescription;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
