package com.example.Models;

public class RateListModel {
    private int imagebox;
    private String title;
    private String body;

    public RateListModel(int imagebox, String title, String body) {
        this.imagebox = imagebox;
        this.title = title;
        this.body = body;

    }

    public int getImagebox() {
        return imagebox;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
