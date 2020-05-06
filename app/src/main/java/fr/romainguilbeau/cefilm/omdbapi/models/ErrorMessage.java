package fr.romainguilbeau.cefilm.omdbapi.models;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {

    @SerializedName("Response")
    private String response;

    @SerializedName("Error")
    private String error;

    ErrorMessage() {
        // no-args constructor for GSON use
    }

    /**
     * Get the "response" attribute
     *
     * @return "response" attribute
     */
    public String getResponse() {
        return response;
    }

    /**
     * Get the "error" attribute
     *
     * @return "error" attribute
     */
    public String getError() {
        return error;
    }
}
