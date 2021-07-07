package com.reakabc.mvvmmovieapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reakabc.mvvmmovieapp.Model.Movie;
import com.reakabc.mvvmmovieapp.R;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> list;
    private ItemClickListener itemClickListener;

    public MovieListAdapter(Context context, List<Movie> list, ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        itemClickListener = clickListener;
    }

    @Nullable
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.title.setText(list.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.movieImage);
            title = itemView.findViewById(R.id.movieTitle);

        }
    }

    public void setMovieList(List<Movie> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(Movie movie);
    }
}
