package org.recap.model.userManagement;

/**
 * Created by dharmendrag on 28/12/16.
 */
public class UserDetailsForm {

    private Integer loginInstitutionId;

    private boolean superAdmin;

    private boolean recapUser;

    private boolean requestAllItems;

    public boolean isRequestAllItems() {
        return requestAllItems;
    }

    public void setRequestAllItems(boolean requestAllItems) {
        this.requestAllItems = requestAllItems;
    }

    public Integer getLoginInstitutionId() {
        return loginInstitutionId;
    }

    public void setLoginInstitutionId(Integer loginInstitutionId) {
        this.loginInstitutionId = loginInstitutionId;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public boolean isRecapUser() {
        return recapUser;
    }

    public void setRecapUser(boolean recapUser) {
        this.recapUser = recapUser;
    }
}
