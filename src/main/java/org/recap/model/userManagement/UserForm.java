package org.recap.model.userManagement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dharmendrag on 29/11/16.
 */
public class UserForm implements Serializable {

    private Integer userId;

    private String username;

    private String password;

    private boolean rememberMe;

    private String wrongCredentials;

    private boolean passwordMatcher;

    public boolean isPasswordMatcher() {
        return passwordMatcher;
    }

    public void setPasswordMatcher(boolean passwordMatcher) {
        this.passwordMatcher = passwordMatcher;
    }

    public String getWrongCredentials() {
        return wrongCredentials;
    }

    public void setWrongCredentials(String wrongCredentials) {
        this.wrongCredentials = wrongCredentials;
    }

    private Set<String> permissions=new HashSet<String>();

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    private int institution;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
