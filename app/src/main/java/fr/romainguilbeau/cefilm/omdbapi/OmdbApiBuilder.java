package fr.romainguilbeau.cefilm.omdbapi;

import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackFailure;
import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackMovie;
import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackMovieList;

public class OmdbApiBuilder {

    /**
     * True if search bu movie list
     */
    boolean searchByList;
    /**
     * A valid IMDb ID (e.g. tt1285016)
     */
    String imdbID;
    /**
     * Movie title to search for.
     */
    String querySearch;
    /**
     * Type of result to return.
     */
    String type;
    /**
     * Year of release
     */
    int year;
    /**
     * Page number to return.
     */
    int page;
    /**
     * Return short or full plot
     */
    String plot;
    /**
     * Version of API KEY
     */
    private String version;
    /**
     * Callback in case of call failure
     */
    CallbackFailure callbackFailure;
    /**
     * Callback movie in case of success
     */
    CallbackMovie callbackMovie;
    /**
     * Callback movies in case of success
     */
    CallbackMovieList callbackMovieList;

    /**
     * Search movie
     */
    private OmdbApiBuilder() {
        this.imdbID = "";
        this.querySearch = "";
        this.type = "";
        this.year = -1;
        this.page = -1;
        this.plot = "full";
        this.version = "1";
        this.callbackFailure = null;
        this.callbackMovie = null;
        this.callbackMovieList = null;
    }

    /**
     * Launch API REST Call
     */
    public void call() {
        new OmdbApi(searchByList, version, imdbID, querySearch, type, year, page, plot,
                callbackFailure, callbackMovie, callbackMovieList
        ).call();
    }

    /**
     * Builder to search movie by ID
     */
    public static class ByID extends OmdbApiBuilder {

        /**
         * Search movie by ID
         *
         * @param imdbID The movie ID
         */
        public ByID(String imdbID) {
            this.searchByList = false;
            this.imdbID = imdbID;
        }

        /**
         * Short or full plot (full by default)
         *
         * @param full true for full plot
         * @return The Builder
         */
        public ByID plot(boolean full) {
            this.plot = full ? "full" : "short";
            return this;
        }

        /**
         * On failure callback
         *
         * @param callbackFailure The callback failure
         * @return The Builder
         */
        public ByID onFailure(CallbackFailure callbackFailure) {
            this.callbackFailure = callbackFailure;
            return this;
        }

        /**
         * On Success callback
         *
         * @param callbackMovie The movie callback
         * @return The Builder
         */
        public ByID onSuccess(CallbackMovie callbackMovie) {
            this.callbackMovie = callbackMovie;
            return this;
        }
    }

    /**
     * Builder to search movie list by title
     */
    public static class BySearch extends OmdbApiBuilder {

        /**
         * Search movie list by title
         *
         * @param querySearch Movie title to search for.
         */
        public BySearch(String querySearch) {
            this.searchByList = true;
            this.page = 1;
            this.querySearch = querySearch;
        }

        /**
         * Filter with type (all if not defined)
         *
         * @param movieType Type of result to return.
         * @return The Builder
         */
        public BySearch type(MovieType movieType) {
            this.type = movieType.getValue();
            return this;
        }

        /**
         * Filter by year (all if not defined)
         *
         * @param year Year of release.
         * @return The Builder
         */
        public BySearch year(int year) {
            this.year = year;
            return this;
        }

        /**
         * Set the wanted page (page 1 by default)
         *
         * @param page Page number to return.
         * @return The Builder
         */
        public BySearch page(int page) {
            this.page = page;
            return this;
        }

        /**
         * On failure callback
         *
         * @param callbackFailure The callback failure
         * @return The Builder
         */
        public BySearch onFailure(CallbackFailure callbackFailure) {
            this.callbackFailure = callbackFailure;
            return this;
        }

        /**
         * On Success callback
         *
         * @param callbackMovieList The movie list callback
         * @return The Builder
         */
        public BySearch onSuccess(CallbackMovieList callbackMovieList) {
            this.callbackMovieList = callbackMovieList;
            return this;
        }
    }

    /**
     * Type of movie
     */
    public enum MovieType {
        MOVIE("movie"),
        SERIES("series"),
        EPISODE("episode");

        /**
         * API value
         */
        private String value;

        /**
         * Create enum
         *
         * @param value API value
         */
        MovieType(String value) {
            this.value = value;
        }

        /**
         * Get API value
         *
         * @return The API value
         */
        String getValue() {
            return this.value;
        }
    }

}
