package com.example.team7project.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team7project.Favorites;
import com.example.team7project.R;
import com.example.team7project.entities.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class MediaItemSelectAdapter extends RecyclerView.Adapter<MediaItemSelectAdapter.MediaItemSelectViewHolder> {

    private static final String TAG = "MediaListAdapter";
    private Context context;
    private List<MediaItem> mediaItems;
    private List<MediaItem> selectedMediaItems;

    public MediaItemSelectAdapter(Context context, List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
        this.context = context;
        this.selectedMediaItems = new ArrayList<>();
    }

    public List<MediaItem> getSelectedMediaItems() {
        return this.selectedMediaItems;
    }

    @NonNull
    @Override
    public MediaItemSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MediaItemSelectViewHolder(LayoutInflater.from(context).inflate(R.layout.view_media_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MediaItemSelectViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.bindTo(mediaItem);
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public class MediaItemSelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MediaItem mediaItem;
        private TextView titleTextView;
        private TextView platformTextView;
        private TextView categoryTextView;

        public MediaItemSelectViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title);
            platformTextView = itemView.findViewById(R.id.platform);
            categoryTextView = itemView.findViewById(R.id.category);
            itemView.setOnClickListener(this);
        }

        public void bindTo(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            titleTextView.setText(mediaItem.getTitle());
            platformTextView.setText(mediaItem.getPlatform().getName());
            categoryTextView.setText(mediaItem.getCategory().getName());
        }

        @Override
        public void onClick(View v) {

            if (selectedMediaItems.contains(this.mediaItem)) {
                v.setBackgroundColor(Color.WHITE);
                selectedMediaItems.remove(this.mediaItem);
            } else {
                v.setBackgroundColor(Color.LTGRAY);
                selectedMediaItems.add(mediaItem);
            }
        }
    }
}
