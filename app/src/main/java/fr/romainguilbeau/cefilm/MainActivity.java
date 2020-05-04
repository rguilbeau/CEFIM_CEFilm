package fr.romainguilbeau.cefilm;

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

import fr.romainguilbeau.cefilm.components.MovieView;
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
     * Movies list title
     */
    private TextView textViewMoviesListTitle;
    /**
     * Button to perform search
     */
    private Button buttonSearch;
    /**
     * Popular movies container
     */
    private LinearLayout linearLayoutMovies;

    /**
     * Create activity
     *
     * @param savedInstanceState saved instance state
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
            MovieView movieView = new MovieView(this, movie);
            movieView.setOnClickListener(this::clickOnMovie);
            linearLayoutMovies.addView(movieView);
        }
        Toast.makeText(this, textViewWelcome.getText(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        textViewWelcome = findViewById(R.id.main_text_view_welcome);
        textViewMoviesListTitle = findViewById(R.id.main_text_view_movies_list_title);
        linearLayoutMovies = findViewById(R.id.main_linear_layout_movies);
        buttonSearch = findViewById(R.id.main_button_search);
    }

    /**
     * Movie click listener
     *
     * @param view The view (Caution : Must be instance of MovieView)
     */
    private void clickOnMovie(View view) {
        if (!(view instanceof MovieView)) {
            throw new InvalidParameterException("View must be instance of MovieView !");
        }

        MovieView movieView = (MovieView) view;
        Toast.makeText(this, "Clique sur " + movieView.getMovie().getTitle(), Toast.LENGTH_SHORT).show();
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
        movies.add(new Movie(R.drawable.bttf, "Retour vers le future", Date.valueOf("1985-10-30")));
        movies.add(new Movie(R.drawable.alien, "Alien, le huitième passager", Date.valueOf("1979-11-12")));
        return movies;
    }

}
