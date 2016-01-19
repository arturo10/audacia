package com.audacia.beans;

import android.graphics.drawable.Drawable;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 12/01/16.
 */
public class Book {
    private String title;
    private String subtitle;
    private String author;
    private String synopsis;
    private String edition;
    private Date publication;
    private String originalLanguage;
    private double duration;
    private Date recordedDay;
    private int pages;
    private int price;
    private Drawable image;
    private String ISBN;
    private List<Fragment> fragments;


    public Book(){
        this.title="";
        this.subtitle="";
        this.author="";
    }

    public Book(String title,String subtitle,String author,
                String synopsis,String edition,
                Date publication,String originalLanguage,
                double duration,Date recordedDay,
                int fragments,int pages,
                int price,Drawable image,String ISBN){

                this.title=title;
                this.subtitle=subtitle;
                this.author=author;
                this.synopsis=synopsis;
                this.edition=edition;
                this.publication=publication;
                this.originalLanguage=originalLanguage;
                this.duration=duration;
                this.recordedDay=recordedDay;
                this.pages=pages;
                this.price=price;
                this.image=image;
                this.ISBN=ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getRecordedDay() {
        return recordedDay;
    }

    public void setRecordedDay(Date recordedDay) {
        this.recordedDay = recordedDay;
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImageUrl(Drawable image) {
        this.image = image;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
