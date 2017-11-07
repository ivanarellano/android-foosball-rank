package com.ivanarellano.foosballrank.ui.rankings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;

import java.util.List;

final public class RankingsAdapter extends RecyclerView.Adapter<RankingsViewHolder> {

    public interface RankingClickListener {
        void onClick(@NonNull Ranking ranking);
    }

    private final List<Ranking> rankings;
    private RankingClickListener rankingClickListener;

    public RankingsAdapter(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankings_row, parent, false);
        return new RankingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankingsViewHolder holder, int position) {
        holder.bind(rankings.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return rankings.size();
    }
}
