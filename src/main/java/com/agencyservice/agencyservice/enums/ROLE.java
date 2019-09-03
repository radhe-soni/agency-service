package com.agencyservice.agencyservice.enums;

public enum ROLE{
    SUPPLIER("Supplier"),
    BUYER("Buyer"),
    ADMIN("Admin");

    private String value;
    private ROLE(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
