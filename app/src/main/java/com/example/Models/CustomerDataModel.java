package com.example.Models;

public class CustomerDataModel {
    String name,phoonenumber,address,imageuri;

    public CustomerDataModel(String name, String phoonenumber, String address, String imageuri) {
        this.name = name;
        this.phoonenumber = phoonenumber;
        this.address = address;
        this.imageuri = imageuri;
    }
    public CustomerDataModel(){}

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoonenumber() {
        return phoonenumber;
    }

    public void setPhoonenumber(String phoonenumber) {
        this.phoonenumber = phoonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
