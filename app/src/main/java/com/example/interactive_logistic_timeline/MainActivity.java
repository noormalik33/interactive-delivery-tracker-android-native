package com.example.interactive_logistic_timeline;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TimelineAdapter adapter;
    private List<TimelineModel> timelineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle System Bars (Code from your screenshot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.timelineRecyclerView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.timelineRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load Data
        initData();

        // Set Adapter
        adapter = new TimelineAdapter(this, timelineList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        timelineList = new ArrayList<>();

        // Simulating Logistics Data
        timelineList.add(new TimelineModel(
                "Order Placed",
                "Your order has been received.",
                "Dec 20, 09:00 AM",
                TimelineModel.Status.COMPLETED
        ));

        timelineList.add(new TimelineModel(
                "Packed",
                "Seller has packed your package.",
                "Dec 21, 02:30 PM",
                TimelineModel.Status.COMPLETED
        ));

        timelineList.add(new TimelineModel(
                "Shipped",
                "Package has left the facility.",
                "Dec 22, 08:00 AM",
                TimelineModel.Status.COMPLETED
        ));

        timelineList.add(new TimelineModel(
                "In Transit",
                "Package is on the way to the delivery hub.",
                "Dec 23, 07:15 AM",
                TimelineModel.Status.ACTIVE
        ));

        timelineList.add(new TimelineModel(
                "Out for Delivery",
                "Rider has picked up your package.",
                "--",
                TimelineModel.Status.PENDING
        ));

        timelineList.add(new TimelineModel(
                "Delivered",
                "Package received by customer.",
                "--",
                TimelineModel.Status.PENDING
        ));
    }
}