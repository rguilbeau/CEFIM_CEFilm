package fr.romainguilbeau.cefilm.omdbapi.models;

import com.google.gson.annotations.SerializedName;

public class MovieRating {

    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;

    MovieRating() {
        // no-args constructor for GSON use
        value = "N/A";
        value = "N/A";
    }

    /**
     * Get the "source" attribute
     *
     * @return "source" attribute
     */
    public String getSource() {
        return source;
    }

    /**
     * Get the "value" attribute
     *
     * @return "value" attribute
     */
    public String getValue() {
        return value;
    }
}
