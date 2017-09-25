package com.gnoemes.booklistingapp.models;

public class Book {

    private String title;
    private String[] authors;
    private String description;
    private String imageURL;
    private String url;

    public Book(String title, String[] authors, String description, String imageURL, String url) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.imageURL = imageURL;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorsString() {
        if (authors.length == 1) {
            return authors[0];
        }
        StringBuilder builder = new StringBuilder();

        for (String s:authors) {
            builder.append(s).append(", ");
        }
        builder.replace(builder.length()-3,builder.length()-1,"");
        return builder.toString();
    }

}
