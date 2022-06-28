package com.example.Models;

public class BiddingModel {
    private String imgUri;
    private String name;
    private String phoonnumber;
    private String address;
    private String biddingprice;
    private String Biddername;


    public BiddingModel(String imgUri, String name, String phoonnumber, String address, String biddingprice, String biddername) {
        this.imgUri = imgUri;
        this.name = name;
        this.phoonnumber = phoonnumber;
        this.address = address;
        this.biddingprice = biddingprice;
        Biddername = biddername;
    }

    public String getBiddername() {
        return Biddername;
    }

    public void setBiddername(String biddername) {
        Biddername = biddername;
    }

    public BiddingModel(){}

    public String getPhoonnumber() {
        return phoonnumber;
    }

    public void setPhoonnumber(String phoonnumber) {
        this.phoonnumber = phoonnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiddingprice() {
        return biddingprice;
    }

    public void setBiddingprice(String biddingprice) {
        this.biddingprice = biddingprice;
    }


    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
