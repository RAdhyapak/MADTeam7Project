package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team7project.entities.AuthUser;
import com.example.team7project.entities.User;
import com.example.team7project.services.RestService;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";

    private EditText username;
    private EditText password;

    private RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        restService = RestService.getInstance();
        initWidgets();

    }

    private void initWidgets() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.buttonLogin);
        Button signUpButton = (Button) findViewById(R.id.signUp);

        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            String uname = username.getText().toString();
            String pass = password.getText().toString();

            if (StringUtils.isNotEmpty(uname) && StringUtils.isNotEmpty(pass)) {
                Gson gson = new Gson();
                String json = gson.toJson(new AuthUser(uname, pass));
                Log.d(TAG, "Json:" + json);
                restService.post(json, "user/login", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        String body = responseBody.string();
                        if (response.isSuccessful()) {
                            User user = gson.fromJson(body, User.class);
                            Log.d(TAG, "User Successfully Logged in as:" + user.toString());
                            Log.d(TAG, "User Successfully Logged in:" + body);
                            Intent intent = new Intent(Login.this, Home.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}