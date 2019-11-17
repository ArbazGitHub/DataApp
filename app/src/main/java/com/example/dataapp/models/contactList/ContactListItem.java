package com.example.dataapp.models.contactList;

import java.io.Serializable;

public class ContactListItem implements Serializable {
    int id;
    String strName;
    String strEmail;
    String strAddress;
    String strNumber;

    public ContactListItem() {
    }

    public ContactListItem(int id, String strName, String strEmail, String strAddress, String strNumber) {
        this.id = id;
        this.strName = strName;
        this.strEmail = strEmail;
        this.strAddress = strAddress;
        this.strNumber = strNumber;
    }

    public ContactListItem(String strName, String strEmail, String strAddress, String strNumber) {
        this.strName = strName;
        this.strEmail = strEmail;
        this.strAddress = strAddress;
        this.strNumber = strNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public String getStrNumber() {
        return strNumber;
    }

    public void setStrNumber(String strNumber) {
        this.strNumber = strNumber;
    }
}
