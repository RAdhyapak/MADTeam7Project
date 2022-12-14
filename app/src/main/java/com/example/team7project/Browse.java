package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team7project.adapters.CategoryAdapter;
import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.Category;
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

public class Browse extends AppCompatActivity {
    private static final String TAG = "Browse";

    private List<Category> categories;
    private RestService rs;

    private CategoryAdapter cAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        rs = RestService.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getCategories(rs);

        //navbar link to home activity
        ImageButton buttonHome = findViewById(R.id.imageButtonHome);
        buttonHome.setOnClickListener(view -> {
            Intent intent = new Intent(Browse.this, Home.class);
            startActivity(intent);
        });

        //navbar link to favorites activity
        ImageButton buttonFavorites = findViewById(R.id.imageButtonFavorites);
        buttonFavorites.setOnClickListener(view -> {
            Intent intent = new Intent(Browse.this, Favorites.class);
            startActivity(intent);
        });

        //profile banner link to profile activity
        ImageButton buttonProfile = findViewById(R.id.imageButtonProfile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(Browse.this, ProfilePage.class);
            startActivity(intent);
        });
    }

    private void getCategories(RestService rs) {
        Gson gson = new Gson();
        rs.get("categories", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(Browse.this, "An error occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                categories = gson.fromJson(body, new TypeToken<List<Category>>(){}.getType());
                runOnUiThread(() -> {
                    cAdapter = new CategoryAdapter(Browse.this, categories);
                    recyclerView.setAdapter(cAdapter);
                });
            }
        });
    }
}