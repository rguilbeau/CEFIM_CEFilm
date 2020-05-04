package fr.romainguilbeau.cefilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.ArrayList;

import fr.romainguilbeau.cefilm.components.MovieItemView;
import fr.romainguilbeau.cefilm.model.Movie;

/**
 * App entry point
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Welcome text view message
     */
    private TextView textViewWelcome;
    /**
     * Button to perform search
     */
    private Button buttonSearch;
    /**
     * Popular movies container
     */
    private LinearLayout linearLayoutMovies;

    /**
     * {{@inheritDoc}}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        textViewWelcome.setText(getString(R.string.main_welcome, "Romain"));
        buttonSearch.setOnClickListener(this::clickOnSearch);

        ArrayList<Movie> movies = getPopularMovies();
        for (Movie movie : movies) {
            MovieItemView movieItemView = new MovieItemView(this, movie);
            movieItemView.setOnClickListener(this::clickOnMovie);
            linearLayoutMovies.addView(movieItemView);
        }
        Toast.makeText(this, textViewWelcome.getText(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        textViewWelcome = findViewById(R.id.main_text_view_welcome);
        linearLayoutMovies = findViewById(R.id.main_linear_layout_movies);
        buttonSearch = findViewById(R.id.main_button_search);
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
        i.putExtra(MovieActivity.EXTRA_MOVIE_TITLE, movieItemView.getMovie().getTitle());
        i.putExtra(MovieActivity.EXTRA_MOVIE_BACKGROUND_PICTURE, movieItemView.getMovie().getBackgroundResourceId());

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
     * Get popular movies
     *
     * @return popular movies
     */
    private ArrayList<Movie> getPopularMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(R.drawable.bttf, R.drawable.bttf_bg, "Retour vers le future", Date.valueOf("1985-10-30")));
        movies.add(new Movie(R.drawable.alien, R.drawable.alien_bg, "Alien, le huitième passager", Date.valueOf("1979-11-12")));
        return movies;
    }

}
