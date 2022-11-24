package com.example.team7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.User;
import com.example.team7project.services.MediaListService;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MediaListAdapter mlAdapter;
    private MediaListService mlService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mlService = new MediaListService();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        User user = getIntent().getParcelableExtra("user");
        mlAdapter = new MediaListAdapter(Home.this, user.getMedialists());
        recyclerView.setAdapter(mlAdapter);
    }
}