package com.roadsafety.ui.rank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roadsafety.R;
import com.roadsafety.tememeticsProvider.ScoreModel;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    Context context;
    List<ScoreModel> scoreModelList;

    public ScoreAdapter(Context context, List<ScoreModel> scoreModelList) {
        this.context = context;
        this.scoreModelList = scoreModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (scoreModelList != null && scoreModelList.size() > 0) {
            ScoreModel model = scoreModelList.get(position);
            holder.rowdata_rank.setText(model.getRank());
            holder.rowdata_name.setText(model.getName());
            holder.rowdata_score.setText(model.getScore());
        }
    }

    @Override
    public int getItemCount() {
        return scoreModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowdata_rank, rowdata_name, rowdata_score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowdata_rank = itemView.findViewById(R.id.rowdata_rank);
            rowdata_name = itemView.findViewById(R.id.rowdata_name);
            rowdata_score = itemView.findViewById(R.id.rowdata_score);
        }
    }
}
