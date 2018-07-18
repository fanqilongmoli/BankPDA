package com.flc.bankpda.base.bean;

import com.google.gson.Gson;

public class LoginRequest {
    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public byte[] getBytes(){
        Gson gson = new Gson();
        return gson.toJson(this).getBytes();
    }
}
