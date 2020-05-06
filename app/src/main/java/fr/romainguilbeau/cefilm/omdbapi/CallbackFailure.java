package fr.romainguilbeau.cefilm.omdbapi;

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
