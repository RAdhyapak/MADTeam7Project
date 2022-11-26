package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Browse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

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
    }
}