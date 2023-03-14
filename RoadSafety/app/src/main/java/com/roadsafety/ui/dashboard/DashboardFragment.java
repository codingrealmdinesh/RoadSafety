package com.roadsafety.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.roadsafety.MainActivity;
import com.roadsafety.ProfilePage;
import com.roadsafety.R;
import com.roadsafety.RewardsPage;
import com.roadsafety.TripsPage;
import com.telematics.demoapp.TripsListActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView city_speed_text, highway_speed_text, city_violations_text, highway_violations_text, phonedistractions_text, fines_text, hint_text;
    private ImageView city_speed_image, highway_speed_image, rewards, avatar, trip;
    private int[] apiGetReportParams()
    {
        int[] params = new int[6];
        params[0] = 20;     // city speed
        params[1] = 60;    // highway speed
        params[2] = 5;      // city limit
        params[3] = 5;      // highway limit
        params[4] = 15;     // phone distractions
        params[5] = 12000;  // fines
        return params;
    }
    private String apiGetQuicktips()
    {
        return "Last week you were overspeeding for 45 kms, it saved just 23 mins";
    }
    private int chooseImageForText(int value)
    {
        int imgRes = 0;
        if(value >=0 && value < 14.2)
        {
            imgRes = R.drawable.speedometer1;
        }
        else if(value >=14.2 && value < 28.6)
        {
            imgRes = R.drawable.speedometer2;
        }
        else if(value >=28.6 && value < 42.8)
        {
            imgRes = R.drawable.speedometer3;
        }
        else if(value >=42.8 && value < 57)
        {
            imgRes = R.drawable.speedometer4;
        }
        else if(value >=57 && value < 71.2)
        {
            imgRes = R.drawable.speedometer5;
        }
        else if(value >= 71.2 && value <= 85.4)
        {
            imgRes = R.drawable.speedometer6;
        }
        else
        {
            imgRes = R.drawable.speedometer7;
        }
        return imgRes;
    }
    private int chooseColorForText(int value)
    {
        String colour = null;
        if(value >=0 && value < 14.2)
        {
            colour = "#1CB14C";
        }
        else if(value >=14.2 && value < 28.6)
        {
            colour = "#6FBE46";
        }
        else if(value >=28.6 && value < 42.8)
        {
            colour = "#C3D84A";
        }
        else if(value >=42.8 && value < 57)
        {
            colour = "#FDD930";
        }
        else if(value >=57 && value < 71.2)
        {
            colour = "#F6A634";
        }
        else if(value >= 71.2 && value <= 85.4)
        {
            colour = "#F37736";
        }
        else
        {
            colour = "#EE3C3A";
        }
        return Color.parseColor(colour);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);



        MainActivity.getTelemetics().apiGetReportParams(params -> {
            city_speed_text = root.findViewById(R.id.city_speed_text);
            city_speed_text.setText(params[0] +"");
            city_speed_text.setTextColor(chooseColorForText(params[0]));
            city_speed_image = root.findViewById(R.id.city_speed_image);
            city_speed_image.setImageResource(chooseImageForText(params[0]));

            highway_speed_text = root.findViewById(R.id.highway_speed_text);
            highway_speed_text.setText(params[1] + "");
            highway_speed_text.setTextColor(chooseColorForText(params[1]));
            highway_speed_image = root.findViewById(R.id.highway_speed_image);
            highway_speed_image.setImageResource(chooseImageForText(params[1]));

            city_violations_text = root.findViewById(R.id.cityspeedviolation_text);
            city_violations_text.setText(params[2]+"");
            city_violations_text.setTextColor(chooseColorForText(5)); // need to update later

            highway_violations_text = root.findViewById(R.id.highwayspeedviolation_text);
            highway_violations_text.setText(params[3]+"");
            highway_violations_text.setTextColor(chooseColorForText(100)); // need to update later

            phonedistractions_text = root.findViewById(R.id.phonedistraction_text);
            phonedistractions_text.setText(params[4]+"");
            phonedistractions_text.setTextColor(chooseColorForText(100)); // need to update later

            fines_text = root.findViewById(R.id.fines_text);
            fines_text.setText("₹" + params[5]+"");
            fines_text.setTextColor(chooseColorForText(100)); // need to update later
        });
        hint_text = root.findViewById(R.id.hint_text);
        hint_text.setText(MainActivity.getTelemetics().apiGetQuickTips());

        rewards = root.findViewById(R.id.rewarddash);
        rewards.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardsPage.class));
            }
        });

        avatar = root.findViewById(R.id.avatardash);
        avatar.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfilePage.class)));

        trip = root.findViewById(R.id.tripdash);
        trip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TripsListActivity.class));
            }
        });

        return root;
    }
}
