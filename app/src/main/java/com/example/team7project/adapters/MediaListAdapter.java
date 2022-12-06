package com.example.team7project.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team7project.CreateMediaList;
import com.example.team7project.MediaItemsActivity;
import com.example.team7project.R;
import com.example.team7project.entities.Favorite;
import com.example.team7project.entities.MediaList;
import com.example.team7project.services.RestService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.ViewHolder> {

    private String parent;
    private List<MediaList> mediaLists;
    private Context context;

    public MediaListAdapter(Context context, List<MediaList> mLists, String parent) {
        this.context = context;
        this.mediaLists = mLists;
        this.parent = parent;
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
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mediaLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private int id;
        private TextView titleTextView;
        private ImageView imageview;
//        private TextView upvotes;
        private ImageView addToFav;
        private TextView subtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title);
            imageview = itemView.findViewById(R.id.mediaListImage);
//            upvotes = itemView.findViewById(R.id.textUpvotes);
            subtitle = itemView.findViewById(R.id.subTitle);
            addToFav = itemView.findViewById(R.id.addToFavs);
            if ("Browse".equals(parent)) {
                addToFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addToFavorite();
                    }
                });
            } else {
                addToFav.setBackgroundResource(0);
            }
            itemView.setOnClickListener(this);
        }

        public void bindTo(MediaList mediaList) {
            id = mediaList.getId();
            titleTextView.setText(mediaList.getTitle());
            Glide.with(context).load(R.drawable.sample).into(imageview);
//            upvotes.setText(String.valueOf(mediaList.getUpvotes()));
            subtitle.setText(mediaList.getUsername() != null? mediaList.getUsername() : "");
        }

        @Override
        public void onClick(View v) {
            // TODO: open mediaItem list
            Intent intent = new Intent(context, MediaItemsActivity.class);
            intent.putExtra("mediaListId", id);
            context.startActivity(intent);
        }

        public void addToFavorite() {
            Gson gson = new Gson();
            String favoriteObj = gson.toJson(new Favorite(id));
            RestService.getInstance().post(favoriteObj, "favorites", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Toast.makeText(context, "Error occured while adding favorites!!", Toast.LENGTH_LONG);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Activity currentActivity = (Activity)context;
                    currentActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(context, "Added to Favorties!!!", Toast.LENGTH_SHORT).show();
                            addToFav.setImageResource(R.drawable.icon_fav);
                        }
                    });
                }
            });
        }
    }
}