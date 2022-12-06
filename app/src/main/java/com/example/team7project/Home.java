package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team7project.adapters.MediaItemAdapter;
import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.MediaList;
import com.example.team7project.entities.User;
import com.example.team7project.services.MediaListService;
import com.example.team7project.services.RestService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Home extends AppCompatActivity {
    private static final String TAG = "MediaItems";

    private List<MediaList> mediaLists;
    private RestService rs;

    // widgets
    private MediaListAdapter mlAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rs = RestService.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getUserMediaLists(rs);

        //navbar link to browse activity
        ImageButton buttonList = findViewById(R.id.imageButtonList);
        buttonList.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Browse.class);
            startActivity(intent);
        });

        //navbar link to favorites activity
        ImageButton buttonFavorites = findViewById(R.id.imageButtonFavorites);
        buttonFavorites.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Favorites.class);
            startActivity(intent);
        });

        //profile banner link to profile activity
        ImageButton buttonProfile = findViewById(R.id.imageButtonProfile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, ProfilePage.class);
            startActivity(intent);
        });

        FloatingActionButton fab = findViewById(R.id.createMediaListButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, CreateMediaList.class);
                startActivity(intent);
            }
        });
    }

    private void getUserMediaLists(RestService rs) {
        Gson gson = new Gson();
        rs.get("medialists", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // TODO: Show a toast
                Toast.makeText(Home.this, "An error occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                if (response.isSuccessful()) {
                    mediaLists = gson.fromJson(body, new TypeToken<List<MediaList>>(){}.getType());
                    runOnUiThread(() -> {
                        mlAdapter = new MediaListAdapter(Home.this, mediaLists, "Home");
                        recyclerView.setAdapter(mlAdapter);
                    });
                } else {
                    Log.d(TAG, "Error fetching MediaList");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}