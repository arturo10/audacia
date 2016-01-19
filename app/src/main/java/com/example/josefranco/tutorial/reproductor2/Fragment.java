package com.example.josefranco.tutorial.reproductor2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 14/01/16.
 */
public class Fragment implements Parcelable{
    private String title;
    private String urlAudio;
    private int duration;

    public Fragment(String title, int duration, String urlAudio) {
        this.title = title;
        this.duration = duration;
        this.urlAudio = urlAudio;
    }
    public Fragment(Parcel in) {
        this.title = in.readString();
        this.duration = in.readInt();
        this.urlAudio = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(duration);
        dest.writeString(urlAudio);

    }

    public static final Creator<Fragment> CREATOR = new Creator<Fragment>() {
        @Override
        public Fragment createFromParcel(Parcel source) {
            return new Fragment(source);
        }

        @Override
        public Fragment[] newArray(int size) {
            return new Fragment[size];
        }
    };
}
