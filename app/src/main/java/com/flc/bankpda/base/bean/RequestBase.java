package com.flc.bankpda.base.bean;

import com.google.gson.Gson;


public class RequestBase {
    private String url;
    private String token;
    private String code;
    private String method;
    private byte[] body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String toRequest() {
        Gson gson = new Gson();
        return gson.toJson(this) + "#end#";
    }
}
