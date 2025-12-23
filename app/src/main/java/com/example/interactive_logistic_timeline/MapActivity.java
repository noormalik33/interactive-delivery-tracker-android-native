package com.example.interactive_logistic_timeline;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get the data passed from the adapter
        String statusName = getIntent().getStringExtra("STATUS_NAME");

        TextView title = findViewById(R.id.mapTitle);
        title.setText("Tracking Location: " + statusName);
    }
}