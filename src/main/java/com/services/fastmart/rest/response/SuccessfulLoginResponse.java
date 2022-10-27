package com.services.fastmart.rest.response;

public class SuccessfulLoginResponse {
    private String userEmail;
    private String accessToken;
    private String username;
    private String prettyName;

    public SuccessfulLoginResponse(String userEmail, String accessToken, String username, String prettyName) {
        this.userEmail = userEmail;
        this.accessToken = accessToken;
        this.username = username;
        this.prettyName = prettyName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }
}
