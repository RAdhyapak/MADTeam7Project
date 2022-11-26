package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.User;
import com.example.team7project.services.MediaListService;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MediaListService mlService = new MediaListService();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        User user = getIntent().getParcelableExtra("user");
        MediaListAdapter mlAdapter = new MediaListAdapter(Home.this, user.getMedialists());
        recyclerView.setAdapter(mlAdapter);

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
        ImageButton buttonProfile = findViewById(R.id.profile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, ProfilePage.class);
            startActivity(intent);
        });
    }
}