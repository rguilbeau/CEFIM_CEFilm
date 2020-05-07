package fr.romainguilbeau.cefilm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.adapters.SearchAdapter;
import fr.romainguilbeau.cefilm.omdbapi.OmdbApiBuilder;
import fr.romainguilbeau.cefilm.omdbapi.models.MovieShort;
import fr.romainguilbeau.cefilm.utils.KeyboardUtils;

public class SearchActivity extends AppCompatActivity {

    /**
     * Movies result view
     */
    private RecyclerView recyclerView;
    /**
     * Recycle view search adapter
     */
    private SearchAdapter searchAdapter;
    /**
     * Movies search result
     */
    private ArrayList<MovieShort> moviesShort;
    /**
     * Edit text query search
     */
    private EditText editTextSearch;
    /**
     * Button for launch search
     */
    private Button buttonGo;
    /**
     * Progress bar
     */
    private ProgressBar progressBar;
    /**
     * Movie types spinner
     */
    private Spinner spinnerTypes;
    /**
     * Displayed page
     */
    private int page = 1;

    /**
     * {{@inheritDoc}}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initComponents();

        moviesShort = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        searchAdapter = new SearchAdapter(this, moviesShort);
        searchAdapter.setOnClickListener(this::clickOnMovie);
        recyclerView.setAdapter(searchAdapter);
        editTextSearch.requestFocus();

        OmdbApiBuilder.MovieType[] movieTypes = OmdbApiBuilder.MovieType.values();
        String[] items = new String[movieTypes.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = findMovieTypeDisplayName(movieTypes[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTypes.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    page++;
                    launchSearch();
                }
            }
        });

        buttonGo.setOnClickListener(v -> {
            page = 1;
            launchSearch();
        });
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        recyclerView = findViewById(R.id.search_recycle_view_movies);
        editTextSearch = findViewById(R.id.search_edit_text_search);
        buttonGo = findViewById(R.id.search_button_go);
        progressBar = findViewById(R.id.search_progress_bar);
        spinnerTypes = findViewById(R.id.search_spinner_types);
    }

    /**
     * Lance la recherche
     */
    private void launchSearch() {
        progressBar.setVisibility(View.VISIBLE);
        KeyboardUtils.hideKeyBoard(this.getCurrentFocus(), this);

        int indexType = spinnerTypes.getSelectedItemPosition();
        OmdbApiBuilder.MovieType movieType = OmdbApiBuilder.MovieType.values()[indexType];

        new OmdbApiBuilder.BySearch(editTextSearch.getText().toString())
                .type(movieType)
                .page(page)
                .onFailure(ex -> showErrorMessage(ex.getMessage()))
                .onSuccess(movieSearch -> {
                    if (page == 1) {
                        moviesShort.clear();
                    }
                    moviesShort.addAll(Arrays.asList(movieSearch.getMovies()));
                    runOnUiThread(() -> {
                        searchAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                    });
                }).call();
    }

    /**
     * Display Movie activity on item click
     *
     * @param view Clicked view
     */
    private void clickOnMovie(View view) {
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        MovieShort movieShort = moviesShort.get(itemPosition);
        if (movieShort != null) {
            Intent i = new Intent(this, MovieActivity.class);
            i.putExtra(MovieActivity.EXTRA_MOVIE_IMDB_ID, movieShort.getImdbID());
            startActivity(i);
        }
    }

    /**
     * Show invalid message
     */
    private void showErrorMessage(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        moviesShort.clear();
        runOnUiThread(() -> {
            searchAdapter.notifyDataSetChanged();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

    /**
     * Find the display name of movie types
     *
     * @param movieType The movie type
     * @return the display name of movie types
     */
    private String findMovieTypeDisplayName(OmdbApiBuilder.MovieType movieType) {
        switch (movieType) {
            case MOVIES:
                return getString(R.string.search_movie_type_movies);
            case SERIES:
                return getString(R.string.search_movie_type_series);
            default:
                return getString(R.string.search_movie_type_all);
        }
    }
}
