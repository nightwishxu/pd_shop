package com.base.web.annotation;

public enum LoginMethod {

    ADMIN("sessionUser", "manager"),
    WEB("webUser", "web/login"),
    PHONE("wapUser", "m/login");

    private String name;
    private String url;

    private LoginMethod(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
