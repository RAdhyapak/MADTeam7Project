package com.example.team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team7project.adapters.MediaItemSelectAdapter;
import com.example.team7project.entities.MediaItem;
import com.example.team7project.entities.MediaList;
import com.example.team7project.services.RestService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CreateMediaList extends AppCompatActivity {

    private RestService rs;
    private List<MediaItem> mediaItemsList;

    // widgets
    private MediaItemSelectAdapter misAdapter;
    private RecyclerView recyclerView;
    private EditText titleText;

    private static String TAG = "CreateMediaList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_media_list);

        rs = RestService.getInstance();

        recyclerView = findViewById(R.id.mediaItemRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        titleText = findViewById(R.id.mediaListName);
        getAllMediaItems(rs);

        FloatingActionButton fab = findViewById(R.id.createMediaListButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Check if the list and title are not empty
                List<MediaItem> newList = misAdapter.getSelectedMediaItems();
                String title = titleText.getText().toString();
                if (StringUtils.isNotEmpty(title) && newList != null && newList.size() > 0) {
                    MediaList newML = new MediaList(-1, title, 0, newList);
                    createMediaList(rs, newML);
                } else {
                    Toast.makeText(CreateMediaList.this, "No Media Items Selected..!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getAllMediaItems(RestService rs) {
        Gson gson = new Gson();
        rs.get("mediaitems", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(CreateMediaList.this, "An error occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                if (response.isSuccessful()) {
                    mediaItemsList = gson.fromJson(body, new TypeToken<List<MediaItem>>(){}.getType());
                    runOnUiThread(() -> {
                        misAdapter = new MediaItemSelectAdapter(CreateMediaList.this, mediaItemsList);
                        recyclerView.setAdapter(misAdapter);
                    });
                } else {
                    Toast.makeText(CreateMediaList.this, "An error occurred", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createMediaList(RestService rs, MediaList newML) {
        Gson gson = new Gson();
        String objString = gson.toJson(newML);
        Log.d(TAG, "Post JSON: " + objString);
        rs.post(objString, "medialists", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(CreateMediaList.this, "Error occured", Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    MediaList newML = gson.fromJson(body, MediaList.class);
                    Intent intent = new Intent(CreateMediaList.this, MediaItemsActivity.class);
                    intent.putExtra("mediaListId", newML.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateMediaList.this, "Error occurred", Toast.LENGTH_LONG);
                }
            }
        });
    }
}