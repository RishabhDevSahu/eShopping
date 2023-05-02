package com.example.eshopping;

public class AddAddressModel {

    private int personID;
    private String userID;
    private String personName;
    private String personAddress;
    private String personLandMark;
    private String personCity;
    private String personState;
    private String personPinCode;
    private String personCountry;
    private String personMobileNumber;

    public AddAddressModel(int personID, String userID, String personName, String personAddress, String personLandMark, String personCity, String personState, String personPinCode, String personCountry, String personMobileNumber) {
        this.personID = personID;
        this.userID = userID;
        this.personName = personName;
        this.personAddress = personAddress;
        this.personLandMark = personLandMark;
        this.personCity = personCity;
        this.personState = personState;
        this.personPinCode = personPinCode;
        this.personCountry = personCountry;
        this.personMobileNumber = personMobileNumber;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonLandMark() {
        return personLandMark;
    }

    public void setPersonLandMark(String personLandMark) {
        this.personLandMark = personLandMark;
    }

    public String getPersonCity() {
        return personCity;
    }

    public void setPersonCity(String personCity) {
        this.personCity = personCity;
    }

    public String getPersonState() {
        return personState;
    }

    public void setPersonState(String personState) {
        this.personState = personState;
    }

    public String getPersonPinCode() {
        return personPinCode;
    }

    public void setPersonPinCode(String personPinCode) {
        this.personPinCode = personPinCode;
    }

    public String getPersonCountry() {
        return personCountry;
    }

    public void setPersonCountry(String personCountry) {
        this.personCountry = personCountry;
    }

    public String getPersonMobileNumber() {
        return personMobileNumber;
    }

    public void setPersonMobileNumber(String personMobileNumber) {
        this.personMobileNumber = personMobileNumber;
    }
}
