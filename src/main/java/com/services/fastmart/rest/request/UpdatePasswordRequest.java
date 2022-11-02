package com.services.fastmart.rest.request;

public class UpdatePasswordRequest {
    private String userEmail;
    private String newPassword;
    public UpdatePasswordRequest() {}
    public UpdatePasswordRequest(String userEmail, String newPassword) {
        this.userEmail = userEmail;
        this.newPassword = newPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}