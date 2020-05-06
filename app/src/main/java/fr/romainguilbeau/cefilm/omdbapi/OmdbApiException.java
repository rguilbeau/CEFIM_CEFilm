package fr.romainguilbeau.cefilm.omdbapi;

import androidx.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Response;

public class OmdbApiException extends Exception {

    private Call call;
    private Response response;
    private Throwable cause;

    OmdbApiException(String message) {
        super(message);
    }

    void setCall(Call call) {
        this.call = call;
    }

    void setResponse(Response response) {
        this.response = response;
    }

    void setCause(Throwable cause) {
        this.cause = cause;
    }

    /**
     * Get the "call" attribute
     *
     * @return "call" attribute
     */
    @Nullable
    public Call getCall() {
        return call;
    }

    /**
     * Get the "response" attribute
     *
     * @return "response" attribute
     */
    @Nullable
    public Response getResponse() {
        return response;
    }

    /**
     * Get the "cause" attribute
     *
     * @return "cause" attribute
     */
    @Nullable
    @Override
    public Throwable getCause() {
        return cause;
    }
}
