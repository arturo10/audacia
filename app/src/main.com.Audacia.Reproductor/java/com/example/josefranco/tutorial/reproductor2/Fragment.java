package com.example.josefranco.tutorial.reproductor2;

/**
 * Created by root on 14/01/16.
 */
public class Fragment {
    private String title;
    private String urlAudio;
    private int duration;

    public Fragment(String title, int duration, String urlAudio) {
        this.title = title;
        this.duration = duration;
        this.urlAudio = urlAudio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
