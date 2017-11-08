package com.ivanarellano.foosballrank.ui.rankings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;
import com.ivanarellano.foosballrank.data.remote.PlayerMatches;
import com.ivanarellano.foosballrank.data.remote.Players;

import java.util.ArrayList;
import java.util.List;

final public class RankingsAdapter extends FirebaseRecyclerAdapter<Ranking, RankingsViewHolder> {

    public interface RankingClickListener {
        void onClick(@NonNull Ranking ranking);
    }

//    private final List<Ranking> rankings;
    private RankingClickListener rankingClickListener;

//    public RankingsAdapter() {
//        rankings = new ArrayList<>();
//    }

    public RankingsAdapter(FirebaseRecyclerOptions<Ranking> options) {
        super(options);
    }


    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankings_row, parent, false);
        return new RankingsViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(RankingsViewHolder holder, int position) {
//        holder.bind(rankings.get(position), position + 1);
//    }

    @Override
    protected void onBindViewHolder(RankingsViewHolder holder, int position, Ranking model) {
        holder.bind(model, position + 1);
    }

//    @Override
//    public int getItemCount() {
//        return rankings.size();
//    }

//    public void populateRankings(@Nullable List<Ranking> rankings) {
//        this.rankings.clear();
//
//        if (rankings != null) {
//            this.rankings.addAll(rankings);
//        }
//    }
}
