package com.example.mediasquare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//    String[] mediaType = {"Movies", "TV Shows", "Books" };
//    String[] mediaName = {"Sample", "Sample 2", "Sample 3", "Sample 4", "Sample 5", "Sample 6" };
    int[] mediaImages = {R.drawable.sampleimage, R.drawable.sampleimage2, R.drawable.sampleimage3,
            R.drawable.sampleimage4, R.drawable.sampleimage5, R.drawable.sampleimage6, R.drawable.sampleimage7, R.drawable.sampleimage8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mediatype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        GridView gridView = findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(this, mediaImages);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener((adapterView, view, position, id) -> Toast.makeText(this, "You clicked on an image" , Toast.LENGTH_SHORT).show());
    
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.LTGRAY);
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}