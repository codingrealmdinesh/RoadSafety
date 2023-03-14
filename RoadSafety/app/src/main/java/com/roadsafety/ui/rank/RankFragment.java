package com.roadsafety.ui.rank;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roadsafety.MainActivity;
import com.roadsafety.R;
import com.roadsafety.ProfilePage;
import com.roadsafety.RewardsPage;
import com.roadsafety.TripsPage;
import com.roadsafety.tememeticsProvider.ScoreModel;
import com.telematics.demoapp.TripsListActivity;

import java.util.ArrayList;
import java.util.List;

public class RankFragment extends Fragment {

    private RankViewModel rankViewModel;
    RecyclerView recyclerView, recyclerView1;
    ScoreAdapter adapter, adapter1;
    private ImageView rewards, avatar, trip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rankViewModel =
                ViewModelProviders.of(this).get(RankViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rank, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView1 = root.findViewById(R.id.recycler_view1);
        rewards = root.findViewById(R.id.rewardrank);
        rewards.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardsPage.class));
            }
        });

        avatar = root.findViewById(R.id.avatarrank);
        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfilePage.class));
            }
        });

        trip = root.findViewById(R.id.triprank);
        trip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TripsListActivity.class));
            }
        });
        setRecyclerView(root);

        return root;
    }

    private void setRecyclerView(View root){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new ScoreAdapter(root.getContext(), MainActivity.getTelemetics().apiGetScores());

        recyclerView.setAdapter(adapter);

        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter1 = new ScoreAdapter(root.getContext(), MainActivity.getTelemetics().apiGetImprovedScore());
        recyclerView1.setAdapter(adapter1);
    }
}
