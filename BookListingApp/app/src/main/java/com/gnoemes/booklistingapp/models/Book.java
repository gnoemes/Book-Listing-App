package com.gnoemes.booklistingapp.models;

import android.media.Image;

public class Book {

    private String title;
    private String author;
    private String desc;
    private String img;

    public Book(String title, String author, String desc, String img) {
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
