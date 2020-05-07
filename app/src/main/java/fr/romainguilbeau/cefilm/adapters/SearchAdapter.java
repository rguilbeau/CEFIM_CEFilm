package fr.romainguilbeau.cefilm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fr.romainguilbeau.cefilm.R;
import fr.romainguilbeau.cefilm.omdbapi.models.MovieShort;

/**
 * Search movie adapter
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    /**
     * Application context
     */
    private Context context;
    /**
     * All movies
     */
    private ArrayList<MovieShort> moviesShort;
    /**
     * Click item listener
     */
    private View.OnClickListener clickListener;

    /**
     * Create new adapter
     *
     * @param context     The application context
     * @param moviesShort All movies to display
     */
    public SearchAdapter(Context context, ArrayList<MovieShort> moviesShort) {
        this.context = context;
        this.moviesShort = moviesShort;
    }

    /**
     * Set the on click listener
     *
     * @param onClickListener The onClickListener
     */
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    /**
     * {{@inheritDoc}}
     */
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie, parent, false);
        v.setOnClickListener(this.clickListener);
        return new SearchAdapter.ViewHolder(v);
    }

    /**
     * {{@inheritDoc}}
     */
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        MovieShort movieShort = moviesShort.get(position);
        holder.textViewTitle.setText(movieShort.getTitle());
        holder.textViewRelease.setText(movieShort.getYear());

        Picasso.get().load(movieShort.getPoster()).into(holder.imageViewPoster, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                holder.imageViewPoster.setImageResource(R.drawable.na_img);
            }
        });
    }

    /**
     * {{@inheritDoc}}
     */
    @Override
    public int getItemCount() {
        return moviesShort.size();
    }

    /**
     * Recycle Item view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Image view to display the poster
         */
        private ImageView imageViewPoster;
        /**
         * Text view to display movie title
         */
        private TextView textViewTitle;
        /**
         * Text view to display release year
         */
        private TextView textViewRelease;

        /**
         * Create new recycle item view
         *
         * @param itemView The item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.item_search_image_view_poster);
            textViewTitle = itemView.findViewById(R.id.item_search_text_view_title);
            textViewRelease = itemView.findViewById(R.id.item_search_text_view_release);
        }
    }
}
