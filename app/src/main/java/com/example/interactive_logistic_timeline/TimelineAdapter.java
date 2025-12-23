package com.example.interactive_logistic_timeline;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Import Button
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private Context context;
    private List<TimelineModel> timelineList;

    public TimelineAdapter(Context context, List<TimelineModel> timelineList) {
        this.context = context;
        this.timelineList = timelineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimelineModel item = timelineList.get(position);

        holder.title.setText(item.getTitle());
        holder.date.setText(item.getTime());
        holder.description.setText(item.getDescription());

        // 1. EXPANSION LOGIC
        boolean isExpanded = item.isExpanded();
        holder.layoutActions.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.description.setMaxLines(isExpanded ? 10 : 2);

        // 2. BUTTON LOGIC (NEW FEATURES)

        // Feature A: Call Driver
        holder.btnCall.setOnClickListener(v -> {
            // Opens the phone dialer with a dummy number
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:03001234567"));
            context.startActivity(intent);
        });

        // Feature B: View Map (Opens the new Activity)
        holder.btnMap.setOnClickListener(v -> {
            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("STATUS_NAME", item.getTitle()); // Pass data to next screen
            context.startActivity(intent);
        });


        // 3. COLOR & ICON LOGIC
        int color;
        int iconRes;

        switch (item.getStatus()) {
            case COMPLETED:
                color = ContextCompat.getColor(context, R.color.status_completed);
                iconRes = R.drawable.ic_check;
                holder.markerCard.setCardElevation(0);
                break;
            case ACTIVE:
                color = ContextCompat.getColor(context, R.color.accent_amber);
                iconRes = R.drawable.ic_truck;
                holder.markerCard.setCardElevation(12);
                break;
            default: // PENDING
                color = ContextCompat.getColor(context, R.color.line_color);
                iconRes = R.drawable.ic_clock;
                holder.markerCard.setCardElevation(0);
                break;
        }

        holder.markerCard.setCardBackgroundColor(color);
        holder.line.setBackgroundColor(color);
        holder.markerIcon.setImageResource(iconRes);

        if (item.getStatus() == TimelineModel.Status.PENDING) {
            holder.line.setBackgroundColor(ContextCompat.getColor(context, R.color.line_color));
        }

        if (position == timelineList.size() - 1) {
            holder.line.setVisibility(View.INVISIBLE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(v -> {
            item.setExpanded(!item.isExpanded());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return timelineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, description;
        CardView markerCard;
        ImageView markerIcon;
        View line;
        LinearLayout layoutActions;
        Button btnCall, btnMap; // Defined Buttons

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            date = itemView.findViewById(R.id.textDate);
            description = itemView.findViewById(R.id.textDescription);
            markerCard = itemView.findViewById(R.id.timelineMarker);
            markerIcon = itemView.findViewById(R.id.imgMarkerIcon);
            line = itemView.findViewById(R.id.timelineLine);
            layoutActions = itemView.findViewById(R.id.layoutActions);

            // Link Buttons
            btnCall = itemView.findViewById(R.id.btnCall);
            btnMap = itemView.findViewById(R.id.btnMap);
        }
    }
}