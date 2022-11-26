package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

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
    }
}