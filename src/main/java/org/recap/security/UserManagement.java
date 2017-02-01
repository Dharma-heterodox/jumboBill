package org.recap.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * Created by dharmendrag on 15/12/16.
 */
public enum UserManagement {

    TOKEN_SPLITER(":"),
    ReCAP("ReCAP"),
    SUPER_ADMIN(1),
    CREATE_USER(1,"To create users,assign roles"),
    WRITE_GCD(2,"To write/edit CGD for own institutions"),
    DEACCESSION(3,"To deaccession records for own institution"),
    REQUEST_PLACE(4,"Ability to place request within own institution"),
    REQUEST_PLACE_ALL(5,"Ability to place request within all institutions"),
    REQUEST_CANCEL(6,"Ability to cancel request within own institution"),
    VIEW_PRINT_REPORTS(7,"Ability to view & print reports"),
    SCSB_SEARCH_EXPORT(8,"Ability to search SCSB and export results"),
    BARCODE_RESTRICTED(9,"Barcode will be restricted for this role"),
    REQUEST_ITEMS(10,"Ability to request items from any institution"),
    REQUEST_CANCEL_ALL(11,"Ability to cancel any request");

    public static final String USER_AUTH="user_auth";

    public static final String ROLE_ID="roleId";

    public static final String USER_AUTHENTICATION="isAuthenticated";

    public static final String USER_ID="userId";

    public static final String USER_TOKEN="token";

    public static final String USER_INSTITUTION="userInstitution";

    public static final String REQUEST_PRIVILEGE="isRequestAllowed";

    public static final String REQUEST_ALL_PRIVILEGE="isRequestAllAllowed";

    public static final String REQUEST_ITEM_PRIVILEGE="isRequestItemAllowed";

    public static final String COLLECTION_PRIVILEGE="isCollectionAllowed";

    public static final String REPORTS_PRIVILEGE="isReportAllowed";

    public static final String SEARCH_PRIVILEGE = "isSearchAllowed";

    public static final String USER_ROLE_PRIVILEGE="isUserRoleAllowed";

    public static final String BARCODE_RESTRICTED_PRIVILEGE="isBarcodeRestricted";

    public static final String DEACCESSION_PRIVILEGE="isDeaccessionAllowed";

    public static final String SUPER_ADMIN_USER="isSuperAdmin";

    public static final String ReCAP_USER="isRecapUser";


    public static final String USER_AUTH_ERRORMSG="authErrorMsg";

    public static final String USER_NAME="userName";


    private String value;

    private int integerValues;

    public int getIntegerValues() {
        return integerValues;
    }

    public void setIntegerValues(int integerValues) {
        this.integerValues = integerValues;
    }

    private Integer permissionId;

    private String permissionDesc;

    public Integer getPermissionId(){
        return this.permissionId;
    }

    public String getPermissionDesc(){
        return this.permissionDesc;
    }

    UserManagement(String value)
    {
        this.value=value;
    }

    UserManagement(int value)
    {
        this.integerValues=value;
    }

    UserManagement(Integer permissionId,String permissionDesc)
    {
        this.permissionId=permissionId;
        this.permissionDesc=permissionDesc;

    }

    public String getValue()
    {
        return this.value;
    }

    public static final String[] userAndInstitution(String token)
    {
        String tokenSeperator= UserManagement.TOKEN_SPLITER.getValue();
        String[] values=new String[2];

        if(token.contains(tokenSeperator))
        {
            values=token.split(tokenSeperator);
        }else
        {
            return null;
        }
        return values;
    }

    public static String unAuthorizedUser(HttpSession session, String moduleName, Logger logger)
    {
        final String loginScreen="redirect:/";
        logger.debug(moduleName+" authorization Rejected for :"+(UsernamePasswordToken)session.getAttribute(USER_TOKEN));
        if(session!=null){
            session.invalidate();
        }
        return loginScreen;
    }






}
