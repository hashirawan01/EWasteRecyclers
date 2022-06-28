package com.example.Models;

public class Item {

    String birdListName,birdPrice;
    int birdListImage;

    public Item(String birdListName, String birdPrice, int birdListImage) {
        this.birdListName = birdListName;
        this.birdPrice = birdPrice;
        this.birdListImage = birdListImage;
    }


    public String getBirdListName() {
        return birdListName;
    }

    public void setBirdListName(String birdListName) {
        this.birdListName = birdListName;
    }

    public String getBirdPrice() {
        return birdPrice;
    }

    public void setBirdPrice(String birdPrice) {
        this.birdPrice = birdPrice;
    }

    public int getBirdListImage() {
        return birdListImage;
    }

    public void setBirdListImage(int birdListImage) {
        this.birdListImage = birdListImage;
    }
}