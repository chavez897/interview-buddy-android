package com.example.interviewbuddy.models;

import java.util.Map;

public class CategoryModel {
    private String firebaseId;
    private String language;
    private String level;
    private String name;

    public CategoryModel(String language, String level, String name) {
        this.language = language;
        this.level = level;
        this.name = name;
    }

    public CategoryModel(String firebaseId, Map<String, Object> data) {
        this.firebaseId = firebaseId;
        this.name = (String) data.get("name");
        this.level = (String) data.get("level");
        this.language = (String) data.get("language");
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
