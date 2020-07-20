package ru.hawoline.onepiecemangareader;

import java.io.Serializable;
import java.util.ArrayList;

public class Chapter implements Serializable {
    private String title;
    private String url;
    private ArrayList<String> frames;

    public Chapter(String title, String url) {
        this.title = title;
        this.url = url;
        frames = new ArrayList<>();
    }
    public Chapter(String title, String url, ArrayList<String> frames) {
        this(title, url);
        this.frames = frames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<String> frames) {
        this.frames = frames;
    }
}
