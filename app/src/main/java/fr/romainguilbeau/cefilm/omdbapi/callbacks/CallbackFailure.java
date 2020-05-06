package fr.romainguilbeau.cefilm.omdbapi.callbacks;

import fr.romainguilbeau.cefilm.omdbapi.OmdbApiException;

/**
 * OmdbApi callback failure
 */
public interface CallbackFailure {

    /**
     * On failure
     *
     * @param ex Exception
     */
    void failure(OmdbApiException ex);

}
