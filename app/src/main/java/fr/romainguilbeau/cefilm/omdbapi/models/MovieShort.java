package fr.romainguilbeau.cefilm.omdbapi.models;

import com.google.gson.annotations.SerializedName;

public class MovieShort {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    MovieShort() {
        // no-args constructor for GSON use
        title = "N/A";
        year = "N/A";
        imdbID = "N/A";
        type = "N/A";
        poster = "N/A";
    }

    /**
     * Get the "title" attribute
     *
     * @return "title" attribute
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the "year" attribute
     *
     * @return "year" attribute
     */
    public String getYear() {
        return year;
    }

    /**
     * Get the "imdbID" attribute
     *
     * @return "imdbID" attribute
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     * Get the "type" attribute
     *
     * @return "type" attribute
     */
    public String getType() {
        return type;
    }

    /**
     * Get the "poster" attribute
     *
     * @return "poster" attribute
     */
    public String getPoster() {
        return poster;
    }
}
