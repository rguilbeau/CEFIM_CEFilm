package fr.romainguilbeau.cefilm.omdbapi.models;

import com.google.gson.annotations.SerializedName;

public class MovieSearch {

    @SerializedName("Search")
    private MovieShort[] movies;

    MovieSearch() {
        // no-args constructor for GSON use
        movies = new MovieShort[]{};
    }

    /**
     * Get the "movies" attribute
     *
     * @return "movies" attribute
     */
    public MovieShort[] getMovies() {
        return movies;
    }
}
