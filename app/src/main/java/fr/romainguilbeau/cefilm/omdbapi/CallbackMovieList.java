package fr.romainguilbeau.cefilm.omdbapi;


import fr.romainguilbeau.cefilm.omdbapi.models.MovieSearch;

/**
 * OmdbApi callback success
 */
public interface CallbackMovieList {

    /**
     * On success
     *
     * @param movieSearch The movies result
     */
    void success(MovieSearch movieSearch);

}
