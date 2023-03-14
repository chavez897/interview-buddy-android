package com.example.interviewbuddy.models;

import java.util.Map;

public class LanguageModel {
    private String firebaseId;
    private String name;
    private String image;

    public LanguageModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public LanguageModel(String firebaseId, Map<String, Object> data) {
        this.firebaseId = firebaseId;
        this.name = (String) data.get("name");
        this.image = (String) data.get("image");
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
