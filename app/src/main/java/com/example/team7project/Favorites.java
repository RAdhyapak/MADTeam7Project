package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Favorites extends AppCompatActivity {
    private static final String TAG = "Favourites";

    private List<MediaList> favs;
    private RestService rs;
    private MediaListAdapter mlAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        rs = RestService.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getUserFavorites();

        //navbar link to home activity
        ImageButton buttonHome = findViewById(R.id.imageButtonHome);
        buttonHome.setOnClickListener(view -> {
            Intent intent = new Intent(Favorites.this, Home.class);
            startActivity(intent);
        });

        //navbar link to browse activity
        ImageButton buttonList = findViewById(R.id.imageButtonList);
        buttonList.setOnClickListener(view -> {
            Intent intent = new Intent(Favorites.this, Browse.class);
            startActivity(intent);
        });

        //profile banner link to profile activity
        ImageButton buttonProfile = findViewById(R.id.imageButtonProfile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(Favorites.this, ProfilePage.class);
            startActivity(intent);
        });
    }

    private void getUserFavorites() {
        Gson gson = new Gson();
        rs.get("favorites", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                if (response.isSuccessful()) {
                    favs = gson.fromJson(body, new TypeToken<List<MediaList>>(){}.getType());
                    runOnUiThread(() -> {
                        mlAdapter = new MediaListAdapter(Favorites.this, favs, "Favs");
                        recyclerView.setAdapter(mlAdapter);
                    });
                } else {
                    Log.d(TAG, "Error fetching MediaList");
                }
            }
        });
    }
}