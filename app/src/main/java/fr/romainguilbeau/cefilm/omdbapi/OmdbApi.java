package fr.romainguilbeau.cefilm.omdbapi;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackFailure;
import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackMovie;
import fr.romainguilbeau.cefilm.omdbapi.callbacks.CallbackMovieList;
import fr.romainguilbeau.cefilm.omdbapi.models.ErrorMessage;
import fr.romainguilbeau.cefilm.omdbapi.models.Movie;
import fr.romainguilbeau.cefilm.omdbapi.models.MovieSearch;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OmdbApi
 */
class OmdbApi {

    /**
     * Private API KEY
     */
    private static final String API_KEY = "45f642ad";
    /**
     * HTTP REST Client
     */
    private final OkHttpClient client;
    /**
     * API REST URL (with http parameters)
     */
    private final String url;
    /**
     * Callback on call failure
     */
    private final CallbackFailure callbackFailure;
    /**
     * Callback on success
     */
    private final CallbackMovie callbackMovie;
    /**
     * Callback on success
     */
    private final CallbackMovieList callbackMovieList;
    /**
     * Flag for type of search (Search list or one movie)
     */
    private final boolean searchByList;

    /**
     * Create OmdbApi (called by the Builder)
     *
     * @param searchByList    true if search list movies
     * @param version         Api key version
     * @param imdbID          ImdbID Movie ID
     * @param querySearch     Query search movies
     * @param year            Year of release
     * @param type            Query search type of movie (movie, series, episode)
     * @param page            Page number.
     * @param plot            Short or full plot
     * @param callbackFailure Callback on call failure
     * @param callbackMovie   Callback on success
     */
    OmdbApi(boolean searchByList, String version, String imdbID, String querySearch, String type, int year, int page, String plot, CallbackFailure callbackFailure, CallbackMovie callbackMovie, CallbackMovieList callbackMovieList) {
        this.client = new OkHttpClient();
        this.searchByList = searchByList;

        StringBuilder url = new StringBuilder("http://www.omdbapi.com/");
        url.append("?apikey=").append(API_KEY);

        if (imdbID != null && !imdbID.equals("")) {
            url.append("&i=").append(imdbID);
        }

        if (querySearch != null && !querySearch.equals("")) {
            url.append("&s=").append(querySearch);
        }

        if (type != null && !type.equals("")) {
            url.append("&type=").append(type);
        }

        if (year > 0) {
            url.append("&y=").append(year);
        }

        if (page >= 1 && page <= 100) {
            url.append("&page=").append(page);
        }

        if (plot != null && !plot.equals("")) {
            url.append("&plot=").append(plot);
        }

        if (version != null && !version.equals("")) {
            url.append("&v=").append(version);
        }

        this.url = url.toString();
        this.callbackFailure = callbackFailure;
        this.callbackMovie = callbackMovie;
        this.callbackMovieList = callbackMovieList;
    }

    /**
     * Call REST API (called by the builder)
     */
    void call() {
        Request request = new Request.Builder().url(this.url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                OmdbApiException omdbApiException = new OmdbApiException(e.getMessage());
                omdbApiException.setCall(call);
                omdbApiException.setCause(e);
                invokeFailure(omdbApiException);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                boolean successInvoked = false;
                String message = "";

                ResponseBody body = response.body();

                if (!response.isSuccessful()) {
                    message = "HTTP request not success";
                } else if (body == null) {
                    message = "Request return empty body";
                } else {
                    String json = body.string();

                    Gson gson = new Gson();
                    ErrorMessage errorMessage = gson.fromJson(json, ErrorMessage.class);

                    if (!errorMessage.getResponse().toLowerCase().equals("true")) {
                        message = errorMessage.getError();
                    } else {
                        if (searchByList) {
                            MovieSearch movieSearch = gson.fromJson(json, MovieSearch.class);
                            invokeMoviesSuccess(movieSearch);
                            successInvoked = true;
                        } else {
                            Movie movie = gson.fromJson(json, Movie.class);
                            invokeMovieSuccess(movie);
                            successInvoked = true;
                        }
                    }
                }

                if (!successInvoked) {
                    OmdbApiException omdbApiException = new OmdbApiException(message);
                    omdbApiException.setCall(call);
                    omdbApiException.setResponse(response);
                    invokeFailure(omdbApiException);
                }
            }
        });
    }

    /**
     * Invoke call failure callback if exists
     *
     * @param ex The failure exception
     */
    private void invokeFailure(OmdbApiException ex) {
        if (callbackFailure != null) {
            callbackFailure.failure(ex);
        }
    }

    /**
     * Invoke movie success callback if exists
     *
     * @param movie The parsed movie
     */
    private void invokeMovieSuccess(Movie movie) {
        if (callbackMovie != null) {
            callbackMovie.success(movie);
        }
    }

    /**
     * Invoke movies success callback if exists
     *
     * @param movieSearch The parsed movies
     */
    private void invokeMoviesSuccess(MovieSearch movieSearch) {
        if (callbackMovieList != null) {
            callbackMovieList.success(movieSearch);
        }
    }
}

