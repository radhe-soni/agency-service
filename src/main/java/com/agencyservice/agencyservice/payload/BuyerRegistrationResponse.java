package com.agencyservice.agencyservice.payload;

import com.agencyservice.agencyservice.enums.ROLE;

import java.util.Set;

public class BuyerRegistrationResponse {
    String userName;
    String message;
    Set<ROLE> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<ROLE> getRoles() {
        return roles;
    }

    public void setRoles(Set<ROLE> roles) {
        this.roles = roles;
    }
}
