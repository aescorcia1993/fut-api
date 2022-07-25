package com.example.demo.models;


public class Post {
    private int id;
    private String title;
    private String body;
    private int userId;

    public Post() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        return "Post{id='" + this.id + "', title='" + this.title + "'}";
    }
}
