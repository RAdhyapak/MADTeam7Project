package com.example.team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.MediaList;
import com.example.team7project.services.RestService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BrowseList extends AppCompatActivity {
    private static final String TAG = "BrowseList";

    private RestService rs;
    private List<MediaList> mediaLists;

    private TextView textView;
    private MediaListAdapter mlAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_list);

        int catId = getIntent().getExtras().getInt("categoryId");
        String catName = getIntent().getExtras().getString("categoryName");

        rs = RestService.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView = findViewById(R.id.latestText);
        textView.setText("Latest Lists in " + catName);
        initBrowseList(catId);
        //navbar link to home activity
        ImageButton buttonHome = findViewById(R.id.imageButtonHome);
        buttonHome.setOnClickListener(view -> {
            Intent intent = new Intent(BrowseList.this, Home.class);
            startActivity(intent);
        });

        //navbar link to favorites activity
        ImageButton buttonFavorites = findViewById(R.id.imageButtonFavorites);
        buttonFavorites.setOnClickListener(view -> {
            Intent intent = new Intent(BrowseList.this, Favorites.class);
            startActivity(intent);
        });

        //profile banner link to profile activity
        ImageButton buttonProfile = findViewById(R.id.imageButtonProfile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(BrowseList.this, ProfilePage.class);
            startActivity(intent);
        });

    }

    private void initBrowseList(int catId) {
        Gson gson = new Gson();
        rs.get("medialists?categoryId=" + catId, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                if (response.isSuccessful()) {
                    mediaLists = gson.fromJson(body, new TypeToken<List<MediaList>>(){}.getType());
                    runOnUiThread(() -> {
                        mlAdapter = new MediaListAdapter(BrowseList.this, mediaLists);
                        recyclerView.setAdapter(mlAdapter);
                    });
                } else {
                    Log.d(TAG, "Error fetching MediaList");
                }
            }
        });
    }
}