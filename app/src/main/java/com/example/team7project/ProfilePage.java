package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team7project.adapters.MediaListAdapter;
import com.example.team7project.entities.MediaList;
import com.example.team7project.entities.User;
import com.example.team7project.services.RestService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProfilePage extends AppCompatActivity {

    private TextView username;
    private TextView email;
    private Button logoutBtn;
    private RestService rs;

    private static String TAG = "ProfilePage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        username = findViewById(R.id.usernameText);
        email = findViewById(R.id.emailText);
        logoutBtn = findViewById(R.id.logoutBtn);

        //navbar link to browse activity
        ImageButton buttonList = findViewById(R.id.imageButtonList);
        buttonList.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, Browse.class);
            startActivity(intent);
        });

        //navbar link to favorites activity
        ImageButton buttonFavorites = findViewById(R.id.imageButtonFavorites);
        buttonFavorites.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, Favorites.class);
            startActivity(intent);
        });

        //profile banner link to profile activity
        ImageButton buttonProfile = findViewById(R.id.imageButtonProfile);
        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, ProfilePage.class);
            startActivity(intent);
        });

        rs = RestService.getInstance();

        initProfile();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rs.get("user/logout", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Toast.makeText(ProfilePage.this, "Error logging out..!!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Intent intent = new Intent(ProfilePage.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private void initProfile() {
        Gson gson = new Gson();
        rs.get("user", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(ProfilePage.this, "Error fetching Profile..!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                if (response.isSuccessful()) {
                    User user = gson.fromJson(body, User.class);
                    runOnUiThread(() -> {
                        username.setText(user.getUsername());
                        email.setText(user.getEmail());
                    });
                } else {
                    Log.d(TAG, "Error fetching Profile");
                }
            }
        });
    }
}