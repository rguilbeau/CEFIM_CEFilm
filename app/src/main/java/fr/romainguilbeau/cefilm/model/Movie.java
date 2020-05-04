package fr.romainguilbeau.cefilm.model;

import androidx.annotation.DrawableRes;

import java.util.Date;

public class Movie {

    @DrawableRes
    private int posterResourceId;

    private String title;

    private Date releaseDate;

    public Movie(int posterResourceId, String title, Date releaseDate) {
        this.posterResourceId = posterResourceId;
        this.title = title;
        this.releaseDate = (Date) releaseDate.clone();
    }

    public int getPosterResourceId() {
        return posterResourceId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return (Date) releaseDate.clone();
    }
}
