package fr.romainguilbeau.cefilm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * App entry point
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Create activity
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
