package com.example.aia;

public class Dictionary {
    private String id;
    private String English;
    private String Korean;
    boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getKorean() {
        return Korean;
    }

    public void setKorean(String korean) {
        Korean = korean;
    }

    public Dictionary(String id, String english, String korean) {
        this.id = id;
        English = english;
        Korean = korean;
    }
}
