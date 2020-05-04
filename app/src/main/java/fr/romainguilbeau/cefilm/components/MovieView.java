package fr.romainguilbeau.cefilm.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.model.Movie;

/**
 * Item movie view
 */
@SuppressLint("ViewConstructor")
public class MovieView extends LinearLayout {

    /**
     * The displayed movie
     */
    private Movie movie;
    /**
     * Movie poster
     */
    private ImageView imageViewPoster;
    /**
     * Movie title
     */
    private TextView textViewTitle;
    /**
     * Movie release date
     */
    private TextView textViewRelease;

    /**
     * Create new movie component
     *
     * @param context App context
     */
    public MovieView(Context context, Movie movie) {
        super(context);
        inflate(context, R.layout.component_movie_view, this);

        initComponents();

        this.movie = movie;
        imageViewPoster.setImageResource(movie.getPosterResourceId());
        textViewTitle.setText(movie.getTitle());
        String dateFormatted = DateFormat.getDateInstance().format(movie.getReleaseDate());
        textViewRelease.setText(getContext().getString(R.string.movie_view_release, dateFormatted));
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        imageViewPoster = findViewById(R.id.movie_view_image_view_poster);
        textViewTitle = findViewById(R.id.movie_view_text_view_title);
        textViewRelease = findViewById(R.id.movie_view_text_view_release);
    }

    /**
     * Get the displayed movie
     *
     * @return the displayed movie
     */
    public Movie getMovie() {
        return movie;
    }
}
