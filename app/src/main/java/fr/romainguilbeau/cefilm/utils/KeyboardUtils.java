package fr.romainguilbeau.cefilm.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Short function for keyboard
 */
public class KeyboardUtils {

    /**
     * Hide keyboard
     *
     * @param view    Focused view
     * @param context The application context
     */
    public static void hideKeyBoard(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
