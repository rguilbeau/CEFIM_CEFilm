package fr.romainguilbeau.cefilm.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.omdbapi.models.Movie;

/**
 * Item movie view
 */
@SuppressLint("ViewConstructor")
public class MovieItemView extends LinearLayout {

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
    public MovieItemView(Context context, Movie movie) {
        super(context);
        inflate(context, R.layout.component_movie_item_view, this);

        initComponents();

        this.movie = movie;
        textViewTitle.setText(movie.getTitle());
        textViewRelease.setText(getContext().getString(R.string.movie_item_view_release, movie.getReleased()));
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        imageViewPoster = findViewById(R.id.movie_item_view_image_view_poster);
        textViewTitle = findViewById(R.id.movie_item_view_text_view_title);
        textViewRelease = findViewById(R.id.movie_item_view_text_view_release);
    }

    /**
     * Get the displayed movie
     *
     * @return the displayed movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Get image view poster
     *
     * @return image view poster
     */
    public ImageView getImageViewPoster() {
        return this.imageViewPoster;
    }
}
