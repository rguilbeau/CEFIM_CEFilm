package fr.romainguilbeau.cefilm.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.util.Set;

/**
 * Favorite movies manager
 */
public class FavoriteMovies {
    /**
     * Shared preference name
     */
    private static final String SP_NAME = "95a6106b-e0fe-428a-bd9e-c8c48d16b8cb";
    /**
     * Shared preference key
     */
    private static final String SP_FAVORITE_MOVIE_KEY = "favorite_movie";
    /**
     * Shared preference instance
     */
    private SharedPreferences sp;

    /**
     * Create new favorite movies manager
     *
     * @param context The application context
     */
    public FavoriteMovies(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Add movie to favorite
     *
     * @param imdbID The movie imdb ID
     */
    public void add(String imdbID) {
        Set<String> favorites = findAll();
        if (!favorites.contains(imdbID)) {
            favorites.add(imdbID);
            SharedPreferences.Editor editor = sp.edit();
            editor.putStringSet(SP_FAVORITE_MOVIE_KEY, favorites);
            editor.apply();
        }

    }

    /**
     * Remove movie from favorite
     *
     * @param imdbID The movie imdb ID
     */
    public void remove(String imdbID) {
        Set<String> favorites = findAll();
        favorites.remove(imdbID);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(SP_FAVORITE_MOVIE_KEY, favorites);
        editor.apply();
    }

    /**
     * Check if movie is in favorite
     *
     * @param imdbID The movie imdb ID
     * @return true if is in favorite, else false
     */
    public boolean contains(String imdbID) {
        Set<String> favorites = findAll();
        return favorites.contains(imdbID);
    }

    /**
     * Get all favorite
     *
     * @return All imdb ID
     */
    public Set<String> findAll() {
        return sp.getStringSet(SP_FAVORITE_MOVIE_KEY, new ArraySet<>());
    }

}
