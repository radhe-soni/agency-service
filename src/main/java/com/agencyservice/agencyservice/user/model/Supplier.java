package com.agencyservice.agencyservice.user.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Document(collection = "user")
@Entity
public class Supplier extends User {

    @NotBlank
    private String fullName;
    @NotBlank
    private String gstNumber;
    @Min(1000000000L)
    @Max(9999999999L)
    private long mobileNumber;
    private Address address;
    @DBRef(lazy = true)
    private Bank bankDetails;

    public Supplier(){
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Bank getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(Bank bankDetails) {
        this.bankDetails = bankDetails;
    }

}
