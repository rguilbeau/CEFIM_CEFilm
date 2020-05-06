package fr.romainguilbeau.cefilm.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.omdbapi.OmdbApiBuilder;
import fr.romainguilbeau.cefilm.omdbapi.models.Movie;
import fr.romainguilbeau.cefilm.sharedpreferences.FavoriteMovies;
import fr.romainguilbeau.cefilm.utils.NetworkUtils;

public class MovieActivity extends AppCompatActivity {

    /**
     * Movie title extra intent key
     */
    public static final String EXTRA_MOVIE_IMDB_ID = "movieImdbId";
    /**
     * Current movie imdb displayed
     */
    private String imdbID = "";
    /**
     * Favorite movies
     */
    private FavoriteMovies favoriteMovies;
    /**
     * Floating button
     */
    private FloatingActionButton fab;
    /**
     * Content progress bar
     */
    private ProgressBar progressBar;
    /**
     * Movie content container
     */
    private LinearLayout linearLayoutMovieContent;
    /**
     * Image for display film poster
     */
    private ImageView imageViewPoster;
    /**
     * Text view for film title
     */
    private TextView textViewTitle;
    /**
     * Text view for film release date
     */
    private TextView textViewRelease;
    /**
     * Text view for film tags (Action, Comedy...)
     */
    private TextView textViewTags;
    /**
     * Text view for film synopsis
     */
    private TextView textViewSynopsis;
    /**
     * text view for display or not all synopsis
     */
    private TextView textViewMoreSynopsis;
    /**
     * Text view for film director name
     */
    private TextView textViewDirector;
    /**
     * Text view for film actors names
     */
    private TextView textViewActors;
    /**
     * Text view for film awards
     */
    private TextView textViewAwards;

    /**
     * Flag for collapse synopsis toggle
     * (By default is not collapsed for calculate number of line)
     */
    private boolean synopsisCollapsed = false;

    /**
     * {{@inheritDoc}}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initComponents();

        favoriteMovies = new FavoriteMovies(this);

        if (!NetworkUtils.isConnected(this)) {
            showErrorMessage(getString(R.string.network_error));
        } else {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                this.imdbID = bundle.getString(EXTRA_MOVIE_IMDB_ID, "");
            }

            new OmdbApiBuilder.ByID(this.imdbID)
                    .onSuccess(this::movieLoaded)
                    .onFailure(ex -> showErrorMessage(ex.getMessage()))
                    .call();

            fab.setImageResource(favoriteMovies.contains(this.imdbID) ? R.drawable.favorite_remove : R.drawable.favorite_add);
            fab.setOnClickListener(this::favoriteButtonClicked);

            textViewMoreSynopsis.setOnClickListener(e -> toggleCollapseSynopsis());
        }
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        Toolbar toolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.movie_fab);

        progressBar = findViewById(R.id.movie_progress_bar);
        linearLayoutMovieContent = findViewById(R.id.movie_linear_layout_content);
        imageViewPoster = findViewById(R.id.movie_image_view_poster);
        textViewTitle = findViewById(R.id.movie_text_view_title);
        textViewRelease = findViewById(R.id.movie_text_view_release);
        textViewTags = findViewById(R.id.movie_text_view_tags);
        textViewSynopsis = findViewById(R.id.movie_text_view_synopsis);
        textViewMoreSynopsis = findViewById(R.id.movie_text_view_more_synopsis);
        textViewDirector = findViewById(R.id.movie_text_view_director);
        textViewActors = findViewById(R.id.movie_text_view_actors);
        textViewAwards = findViewById(R.id.movie_text_view_awards);
    }

    /**
     * Update movie UI
     */
    private void movieLoaded(Movie movie) {
        runOnUiThread(() -> {
            Picasso.get().load(movie.getPoster()).into(imageViewPoster, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    imageViewPoster.setImageResource(R.drawable.na_img);
                }
            });
            textViewTitle.setText(movie.getTitle());
            textViewRelease.setText(movie.getReleased());
            textViewTags.setText(movie.getGenre());
            textViewSynopsis.setText(movie.getPlot());
            textViewDirector.setText(movie.getDirector());
            textViewActors.setText(movie.getActors());
            textViewAwards.setText(movie.getAwards());

            progressBar.setVisibility(View.INVISIBLE);
            linearLayoutMovieContent.setVisibility(View.VISIBLE);
            getWindow().getDecorView().post(this::hideMoreSynopsisButtonIfNeeded);
        });
    }

    /**
     * Show invalid message
     */
    private void showErrorMessage(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        linearLayoutMovieContent.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Toggle for hide or not the synopsis
     */
    private void toggleCollapseSynopsis() {
        if (synopsisCollapsed) {
            textViewSynopsis.setMaxLines(Integer.MAX_VALUE);
            textViewMoreSynopsis.setText(R.string.movie_less_synopsis);
        } else {
            textViewSynopsis.setMaxLines(getResources().getInteger(R.integer.movie_max_synopsis_lines));
            textViewMoreSynopsis.setText(R.string.movie_more_synopsis);
        }
        synopsisCollapsed = !synopsisCollapsed;
    }

    /**
     * Hide the "More" synopsis TextView if needed
     */
    private void hideMoreSynopsisButtonIfNeeded() {
        if (textViewSynopsis.getLineCount() <= getResources().getInteger(R.integer.movie_max_synopsis_lines)) {
            textViewMoreSynopsis.setVisibility(View.INVISIBLE);
        } else {
            synopsisCollapsed = false;
            toggleCollapseSynopsis();
        }
    }

    /**
     * When favorite button was clicked, add or remove the current displayed movie from favorite
     *
     * @param view The floating button
     */
    private void favoriteButtonClicked(View view) {
        if (favoriteMovies.contains(this.imdbID)) {
            runOnUiThread(() -> fab.setImageResource(R.drawable.favorite_add));
            favoriteMovies.remove(this.imdbID);
        } else {
            runOnUiThread(() -> fab.setImageResource(R.drawable.favorite_remove));
            favoriteMovies.add(this.imdbID);
            favoriteMovies.add("tt3896198");

        }
    }
}
