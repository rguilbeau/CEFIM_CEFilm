package fr.romainguilbeau.cefilm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MovieActivity extends AppCompatActivity {

    /**
     * Movie title extra intent key
     */
    public static final String EXTRA_MOVIE_TITLE = "movieTitle";
    /**
     * Movie background picture extra intent key
     */
    public static final String EXTRA_MOVIE_BACKGROUND_PICTURE = "bgPicture";
    /**
     * Background image
     */
    private ImageView imageViewBackground;
    /**
     * Floating button
     */
    private FloatingActionButton fab;

    /**
     * {{@inheritDoc}}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initComponents();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            setTitle(extras.getString(EXTRA_MOVIE_TITLE, ""));

            if (extras.containsKey(EXTRA_MOVIE_BACKGROUND_PICTURE)) {
                imageViewBackground.setVisibility(View.VISIBLE);
                imageViewBackground.setImageResource(extras.getInt(EXTRA_MOVIE_BACKGROUND_PICTURE));
            }
        }

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    /**
     * Initialize all components
     */
    private void initComponents() {
        Toolbar toolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.movie_fab);
        imageViewBackground = findViewById(R.id.movie_image_view_background);
    }
}
