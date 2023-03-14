package com.roadsafety.ui.paychallan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.roadsafety.ProfilePage;
import com.roadsafety.R;
import com.roadsafety.RewardsPage;
import com.roadsafety.TripsPage;
import com.telematics.demoapp.TripsListActivity;

public class PayChallanFragment extends Fragment {

    private PayChallanViewModel notificationsViewModel;
    private ImageView rewards, avatar, trip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(PayChallanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_challan, container, false);

        rewards = root.findViewById(R.id.rewardchallan);
        rewards.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardsPage.class));
            }
        });

        avatar = root.findViewById(R.id.avatarchallan);
        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfilePage.class));
            }
        });

        trip = root.findViewById(R.id.tripchallan);
        trip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TripsListActivity.class));
            }
        });
        return root;
    }
}
