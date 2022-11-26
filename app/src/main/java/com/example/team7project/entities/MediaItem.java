package com.example.team7project.entities;

public class MediaItem {

    private int id;
    private String title;
    private Category category;
    private Platform platform;

    public MediaItem(int id, String title, Category category, Platform platform) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category.toString() +
                ", platform=" + platform.toString() +
                '}';
    }
}