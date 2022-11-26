package com.example.team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.team7project.adapters.MediaItemAdapter;
import com.example.team7project.entities.MediaList;
import com.example.team7project.services.RestService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MediaItemsActivity extends AppCompatActivity {
    private static final String TAG = "MediaItems";

    RestService rs;
    MediaList mediaList;

    // widgets
    private RecyclerView recyclerView;
    private MediaItemAdapter miAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_items);
        rs = RestService.getInstance();
        int mediaListId = getIntent().getIntExtra("mediaListId", -1);
        if (mediaListId != -1) {
            Gson gson = new Gson();
            rs.get("medialists/" + mediaListId, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    if (response.isSuccessful()) {
                        mediaList = gson.fromJson(body, MediaList.class);
                        Log.d(TAG, "MediaItems fetched as:" + mediaList.toString());
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MediaItemsActivity.this));
                        miAdapter = new MediaItemAdapter(MediaItemsActivity.this, mediaList.getMediaItems());
                        recyclerView.setAdapter(miAdapter);
                    } else {
                        Log.d(TAG, "Error fetching MediaList");
                    }
                }
            });
        }
    }
}