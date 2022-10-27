package com.services.fastmart.rest.request;

public class SignupRequest {
    private String userEmail;
    private String password;
    private String userName;
    private String prettyName;

    public SignupRequest(String userEmail, String password, String userName, String prettyName) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.prettyName = prettyName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }
}
