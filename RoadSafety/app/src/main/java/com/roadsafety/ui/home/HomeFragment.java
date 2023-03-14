package com.roadsafety.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.roadsafety.MainActivity;
import com.roadsafety.R;
import com.roadsafety.ProfilePage;
import com.roadsafety.RewardsPage;
import com.roadsafety.TripsPage;
import com.telematics.demoapp.TripsListActivity;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView breaking;
    private TextView accelerating;
    private TextView speed;
    private TextView phoneDiscipline;
    private ImageView rewards, avatar, trip;
    private Button starttrip;
    int i = 0;

    public int chooseColorForText(int value)
    {
        String colour = null;
        if(value >=0 && value < 16.6)
        {
            colour = "#ED4824";
        }
        else if(value >=16.6 && value < 33.2)
        {
            colour = "#F5841E";
        }
        else if(value >=33.2 && value < 50)
        {
            colour = "#FBC70F";
        }
        else if(value >=50 && value < 66.6)
        {
            colour = "#CEDC27";
        }
        else if(value >=66.6 && value < 83.2)
        {
            colour = "#91C83E";
        }
        else if(value >= 83.2 && value <= 100)
        {
            colour = "#65BC49";
        }
        return Color.parseColor(colour);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        progressBar = root.findViewById(R.id.progress_bar);
        progressText = root.findViewById(R.id.progress_text);
        MainActivity.getTelemetics().apiFinalScore(value -> {
            progressText.setText(value + "");
            progressBar.setProgress(value);
        });

        breaking = root.findViewById(R.id.breakingText);
        accelerating = root.findViewById(R.id.acceleratingText);
        speed = root.findViewById(R.id.speedText);
        phoneDiscipline = root.findViewById(R.id.phonedisciplineText);

        MainActivity.getTelemetics().apiScoreParameters(params -> {
            breaking.setText(params[0] + "");
            accelerating.setText(params[1] + "");
            speed.setText(params[2] + "");
            phoneDiscipline.setText(params[3] + "");
            breaking.setTextColor(chooseColorForText(params[0]));
            accelerating.setTextColor(chooseColorForText(params[1]));
            speed.setTextColor(chooseColorForText(params[2]));
            phoneDiscipline.setTextColor(chooseColorForText(params[3]));
        });

        rewards = root.findViewById(R.id.rewardhome);
        rewards.setOnClickListener(v -> startActivity(new Intent(getActivity(), RewardsPage.class)));

        avatar = root.findViewById(R.id.avatarhome);
        avatar.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfilePage.class)));

        trip = root.findViewById(R.id.triphome);
        trip.setOnClickListener(v -> startActivity(new Intent(getActivity(), TripsListActivity.class)));
        starttrip = root.findViewById(R.id.starttrip);
        if(MainActivity.getTelemetics().isTracking()){
            starttrip.setText("Stop Trip");
        }
        else {
            starttrip.setText("Start Trip");
        }
        starttrip.setOnClickListener(v -> {
            if (starttrip.getText() == "Stop Trip")
            {
                MainActivity.getTelemetics().stopTracking();
                starttrip.setText("Start Trip");
            }
            else
            {
                MainActivity.getTelemetics().startTracking();
                starttrip.setText("Stop Trip");
            }
        });
        return root;
    }
}
