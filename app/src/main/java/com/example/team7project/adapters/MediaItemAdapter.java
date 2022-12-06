package com.example.team7project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team7project.R;
import com.example.team7project.entities.MediaItem;

import org.w3c.dom.Text;

import java.util.List;

public class MediaItemAdapter extends RecyclerView.Adapter<MediaItemAdapter.ViewHolder> {

    private Context context;
    private List<MediaItem> mediaItems;

    public MediaItemAdapter(Context context, List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_media_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.bindTo(mediaItem);
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageview;
        private TextView titleTextView;
        private TextView platformTextView;
        private TextView categoryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.mediaItemImage);
            titleTextView = itemView.findViewById(R.id.title);
            platformTextView = itemView.findViewById(R.id.platform);
            categoryTextView = itemView.findViewById(R.id.category);
        }

        public void bindTo(MediaItem mediaItem) {
            titleTextView.setText(mediaItem.getTitle());
            int itemImg = context.getResources().getIdentifier(mediaItem.getImg(),
                    "drawable", context.getPackageName());
            Glide.with(context).load(itemImg).into(imageview);
            platformTextView.setText(mediaItem.getPlatform().getName());
            categoryTextView.setText(mediaItem.getCategory().getName());
        }

        @Override
        public void onClick(View v) {
            // TODO: open mediaItem list
        }
    }
}
