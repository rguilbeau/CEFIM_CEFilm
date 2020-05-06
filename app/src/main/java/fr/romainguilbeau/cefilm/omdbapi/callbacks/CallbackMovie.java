package fr.romainguilbeau.cefilm.omdbapi.callbacks;


import fr.romainguilbeau.cefilm.omdbapi.models.Movie;

/**
 * OmdbApi callback success
 */
public interface CallbackMovie {

    /**
     * On success
     *
     * @param movie The retrieve movie
     */
    void success(Movie movie);

}
