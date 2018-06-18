package com.webzone.allbillpay.modal;

/**
 * Created by Jitendra Soam on 1/14/2017.
 */

public class User {

    private String LoginId;
    private String LoginType;
    private String LoginName;
    private String LoginEmail;
    private String LoginMobile;
    private String LoginProfile;

    public User() {
    }

    public String getLoginType() {
        return LoginType;
    }

    public void setLoginType(String loginType) {
        LoginType = loginType;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getLoginEmail() {
        return LoginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        LoginEmail = loginEmail;
    }

    public String getLoginMobile() {
        return LoginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        LoginMobile = loginMobile;
    }

    public String getLoginProfile() {
        return LoginProfile;
    }

    public void setLoginProfile(String loginProfile) {
        LoginProfile = loginProfile;
    }
}
