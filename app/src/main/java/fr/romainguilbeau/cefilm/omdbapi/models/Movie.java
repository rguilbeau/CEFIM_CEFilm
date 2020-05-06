package fr.romainguilbeau.cefilm.omdbapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie extends MovieShort {

    @SerializedName("Rated")
    private String rated;

    @SerializedName("Released")
    private String released;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Language")
    private String language;

    @SerializedName("Country")
    private String country;

    @SerializedName("Awards")
    private String awards;

    @SerializedName("Ratings")
    private ArrayList<MovieRating> movieRatings;

    @SerializedName("Metascore")
    private String metascore;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("imdbVotes")
    private String imdbVotes;

    @SerializedName("DVD")
    private String dvd;

    @SerializedName("BoxOffice")
    private String boxOffice;

    @SerializedName("Production")
    private String production;

    @SerializedName("Website")
    private String website;

    @SerializedName("Response")
    private String response;

    Movie() {
        // no-args constructor for GSON use
        rated = "N/A";
        released = "N/A";
        runtime = "N/A";
        genre = "N/A";
        director = "N/A";
        writer = "N/A";
        actors = "N/A";
        plot = "N/A";
        language = "N/A";
        country = "N/A";
        awards = "N/A";
        movieRatings = new ArrayList<>();
        metascore = "N/A";
        imdbRating = "N/A";
        imdbVotes = "N/A";
        dvd = "N/A";
        boxOffice = "N/A";
        production = "N/A";
        website = "N/A";
        response = "N/A";
    }

    /**
     * Get the "rated" attribute
     *
     * @return "rated" attribute
     */
    public String getRated() {
        return rated;
    }

    /**
     * Get the "released" attribute
     *
     * @return "released" attribute
     */
    public String getReleased() {
        return released;
    }

    /**
     * Get the "runtime" attribute
     *
     * @return "runtime" attribute
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * Get the "genre" attribute
     *
     * @return "genre" attribute
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Get the "director" attribute
     *
     * @return "director" attribute
     */
    public String getDirector() {
        return director;
    }

    /**
     * Get the "writer" attribute
     *
     * @return "writer" attribute
     */
    public String getWriter() {
        return writer;
    }

    /**
     * Get the "actors" attribute
     *
     * @return "actors" attribute
     */
    public String getActors() {
        return actors;
    }

    /**
     * Get the "plot" attribute
     *
     * @return "plot" attribute
     */
    public String getPlot() {
        return plot;
    }

    /**
     * Get the "language" attribute
     *
     * @return "language" attribute
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Get the "country" attribute
     *
     * @return "country" attribute
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the "awards" attribute
     *
     * @return "awards" attribute
     */
    public String getAwards() {
        return awards;
    }

    /**
     * Get the "movieRatings" attribute
     *
     * @return "movieRatings" attribute
     */
    public ArrayList<MovieRating> getMovieRatings() {
        //noinspection unchecked
        return (ArrayList<MovieRating>) movieRatings.clone();
    }

    /**
     * Get the "metascore" attribute
     *
     * @return "metascore" attribute
     */
    public String getMetascore() {
        return metascore;
    }

    /**
     * Get the "imdbRating" attribute
     *
     * @return "imdbRating" attribute
     */
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     * Get the "imdbVotes" attribute
     *
     * @return "imdbVotes" attribute
     */
    public String getImdbVotes() {
        return imdbVotes;
    }

    /**
     * Get the "dvd" attribute
     *
     * @return "dvd" attribute
     */
    public String getDvd() {
        return dvd;
    }

    /**
     * Get the "boxOffice" attribute
     *
     * @return "boxOffice" attribute
     */
    public String getBoxOffice() {
        return boxOffice;
    }

    /**
     * Get the "production" attribute
     *
     * @return "production" attribute
     */
    public String getProduction() {
        return production;
    }

    /**
     * Get the "website" attribute
     *
     * @return "website" attribute
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Get the "response" attribute
     *
     * @return "response" attribute
     */
    public String getResponse() {
        return response;
    }
}
