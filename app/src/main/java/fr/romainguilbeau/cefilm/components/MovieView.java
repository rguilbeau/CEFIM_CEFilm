package fr.romainguilbeau.cefilm.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import org.jetbrains.annotations.Nullable;

import fr.romainguilbeau.cefilm.R;

/**
 * Item movie view
 */
public class MovieView extends LinearLayout {

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
    public MovieView(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * Create new movie component with attribute xml
     *
     * @param context App context
     * @param attrs   Xml attributes
     */
    public MovieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Initialize view
     *
     * @param context App context
     * @param attrs   Xml attributes
     */
    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.component_movie_view, this);
        initComponents();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView);

        int posterResource = typedArray.getResourceId(R.styleable.MovieView_poster, R.drawable.na_img);
        String titleValue = typedArray.getString(R.styleable.MovieView_title);
        String releaseDateValue = typedArray.getString(R.styleable.MovieView_release);

        typedArray.recycle();

        String naText = context.getString(R.string.na);
        titleValue = titleValue == null || titleValue.equals("") ? naText : titleValue;
        releaseDateValue = releaseDateValue == null || releaseDateValue.equals("") ? naText : releaseDateValue;

        setResourcePoster(posterResource);
        setMovieTitle(titleValue);
        setMovieReleaseDate(releaseDateValue);
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
     * Set the movie poster
     *
     * @param poserResourceId Drawable resource id
     */
    public void setResourcePoster(@DrawableRes int poserResourceId) {
        imageViewPoster.setImageResource(poserResourceId);
    }

    /**
     * Set the movie title
     *
     * @param movieTitle The movie title
     */
    public void setMovieTitle(String movieTitle) {
        textViewTitle.setText(movieTitle);
    }

    /**
     * Set the movie release date
     *
     * @param strMovieReleaseDate The movie release date (Caution, String value !)
     */
    public void setMovieReleaseDate(String strMovieReleaseDate) {
        textViewRelease.setText(getContext().getString(R.string.movie_view_release, strMovieReleaseDate));
    }
}
