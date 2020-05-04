package fr.romainguilbeau.cefilm.model;

import androidx.annotation.DrawableRes;

import java.util.Date;

public class Movie {

    /**
     * Resource ID for movie poster
     */
    @DrawableRes
    private int posterResourceId;
    /**
     * Resource ID for movie background
     */
    @DrawableRes
    private int backgroundResourceId;
    /**
     * Movie title
     */
    private String title;
    /**
     * Movie release date
     */
    private Date releaseDate;

    /**
     * Create new movie
     *
     * @param posterResourceId     Resource ID for movie poster
     * @param backgroundResourceId Resource ID for movie background
     * @param title                Movie title
     * @param releaseDate          Movie release date
     */
    public Movie(int posterResourceId, int backgroundResourceId, String title, Date releaseDate) {
        this.posterResourceId = posterResourceId;
        this.backgroundResourceId = backgroundResourceId;
        this.title = title;
        this.releaseDate = (Date) releaseDate.clone();
    }

    /**
     * Get the resource ID for movie poster
     *
     * @return The resource ID for movie poster
     */
    public int getPosterResourceId() {
        return posterResourceId;
    }

    /**
     * Get the resource ID for movie background
     *
     * @return The resource ID for movie background
     */
    public int getBackgroundResourceId() {
        return backgroundResourceId;
    }

    /**
     * Get the movie title
     *
     * @return The movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the movie release date
     *
     * @return The movie release date
     */
    public Date getReleaseDate() {
        return (Date) releaseDate.clone();
    }
}
