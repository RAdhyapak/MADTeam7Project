package com.example.team7project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team7project.MediaItemsActivity;
import com.example.team7project.R;
import com.example.team7project.entities.MediaList;

import java.util.List;

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.ViewHolder> {

    private List<MediaList> mediaLists;
    private Context context;

    public MediaListAdapter(Context context, List<MediaList> mLists) {
        this.context = context;
        this.mediaLists = mLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_media_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MediaListAdapter.ViewHolder holder, int position) {
        MediaList mediaList = mediaLists.get(position);
        holder.bindTo(mediaList);
    }

    @Override
    public int getItemCount() {
        return mediaLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private int id;
        private TextView titleTextView;
        private ImageView imageview;
        private TextView upvotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title);
            imageview = itemView.findViewById(R.id.mediaListImage);
            upvotes = itemView.findViewById(R.id.textUpvotes);
            itemView.setOnClickListener(this);
        }

        public void bindTo(MediaList mediaList) {
            id = mediaList.getId();
            titleTextView.setText(mediaList.getTitle());
            Glide.with(context).load(R.drawable.sample).into(imageview);
            upvotes.setText(String.valueOf(mediaList.getUpvotes()));
        }

        @Override
        public void onClick(View v) {
            // TODO: open mediaItem list
            Intent intent = new Intent(context, MediaItemsActivity.class);
            intent.putExtra("mediaListId", id);
            context.startActivity(intent);
        }
    }
}