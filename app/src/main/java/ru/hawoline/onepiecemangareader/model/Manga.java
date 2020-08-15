package ru.hawoline.onepiecemangareader.model;

import java.io.Serializable;
import java.util.ArrayList;

import ru.hawoline.onepiecemangareader.model.Chapter;

public class Manga implements Serializable {
    private String baseUrl;
    private ArrayList<Chapter> chapters;

    public Manga(String baseUrl) {
        this.baseUrl = baseUrl;
        this.chapters = new ArrayList<>();
    }

    public Manga(String baseUrl, ArrayList<Chapter> chapters) {
        this(baseUrl);
        this.chapters = chapters;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }
}
