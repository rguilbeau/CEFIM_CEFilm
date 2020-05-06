package fr.romainguilbeau.cefilm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.InvalidParameterException;
import java.util.Set;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.components.MovieItemView;
import fr.romainguilbeau.cefilm.omdbapi.OmdbApiBuilder;
import fr.romainguilbeau.cefilm.omdbapi.models.Movie;
import fr.romainguilbeau.cefilm.sharedpreferences.FavoriteMovies;

/**
 * App entry point
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Favorite movies manager
     */
    private FavoriteMovies favoriteMovies;
    /**
     * Welcome text view message
     */
    private TextView textViewWelcome;
    /**
     * Favorite movie progress bar
     */
    private ProgressBar progressBar;
    /**
     * Button to perform search
     */
    private Button buttonSearch;
    /**
     * Popular movies container
     */
    private LinearLayout linearLayoutMovies;
    /**
     * Counter for add movie in list
     */
    private int favoriteMovieCnt = 0;

    /**
     * {{@inheritDoc}}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        favoriteMovies = new FavoriteMovies(this);

        textViewWelcome.setText(getString(R.string.main_welcome, "Romain"));
        buttonSearch.setOnClickListener(this::clickOnSearch);

        Toast.makeText(this, textViewWelcome.getText(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Refresh favorite list on resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        linearLayoutMovies.removeAllViews();
        Set<String> movies = favoriteMovies.findAll();
        for (String imdbID : movies) {
            new OmdbApiBuilder.ByID(imdbID)
                    .onFailure(ex -> showErrorMessage(ex.getMessage()))
                    .onSuccess(this::addMovieToFavoriteList)
                    .call();
        }
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        textViewWelcome = findViewById(R.id.main_text_view_welcome);
        progressBar = findViewById(R.id.main_progress_bar_favorite);
        linearLayoutMovies = findViewById(R.id.main_linear_layout_movies);
        buttonSearch = findViewById(R.id.main_button_search);
    }

    /**
     * Add movie to favorite list
     *
     * @param movie The movie to add
     */
    private void addMovieToFavoriteList(Movie movie) {
        runOnUiThread(() -> {
            favoriteMovieCnt++;

            MovieItemView movieItemView = new MovieItemView(this, movie);
            movieItemView.setOnClickListener(this::clickOnMovie);
            linearLayoutMovies.addView(movieItemView);

            if (favoriteMovieCnt >= favoriteMovies.findAll().size()) {
                progressBar.setVisibility(View.INVISIBLE);
                linearLayoutMovies.setVisibility(View.VISIBLE);
            }

            Picasso.get().load(movie.getPoster()).into(movieItemView.getImageViewPoster(), new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    movieItemView.getImageViewPoster().setImageResource(R.drawable.na_img);
                }
            });
        });
    }

    /**
     * Movie click listener
     *
     * @param view The view (Caution : Must be instance of MovieView)
     */
    private void clickOnMovie(View view) {
        if (!(view instanceof MovieItemView)) {
            throw new InvalidParameterException("View must be instance of MovieView !");
        }

        MovieItemView movieItemView = (MovieItemView) view;

        Intent i = new Intent(this, MovieActivity.class);
        i.putExtra(MovieActivity.EXTRA_MOVIE_IMDB_ID, movieItemView.getMovie().getImdbID());
        startActivity(i);
    }

    /**
     * Search button listener
     *
     * @param v Button view
     */
    private void clickOnSearch(View v) {
        Toast.makeText(this, "Clique sur rechercher", Toast.LENGTH_SHORT).show();
    }

    /**
     * Show invalid message
     */
    private void showErrorMessage(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        linearLayoutMovies.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
