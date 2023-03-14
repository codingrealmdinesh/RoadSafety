package com.roadsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.telematics.demoapp.TripsListActivity;

public class RewardsPage extends AppCompatActivity {

    private ImageView avatar, trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_page);

        avatar = findViewById(R.id.avatarreward);
        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ProfilePage.class));
            }
        });
        trip = findViewById(R.id.tripreward);
        trip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TripsListActivity.class));
            }
        });
    }
}