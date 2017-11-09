package com.ivanarellano.foosballrank.ui.rankings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;
import com.ivanarellano.foosballrank.data.RankingSnapshotParser;
import com.ivanarellano.foosballrank.data.remote.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

final public class RankingsFragment extends Fragment {

    private static final String TAG = RankingsFragment.class.getSimpleName();

    public static final String PLAYERS = "players";
    public static final String MATCHES = "matches";
    public static final String PLAYER_MATCHES = "player-matches";

    @BindView(R.id.rv_rankings_list) RecyclerView rankingsRecyclerView;

    private Unbinder unbinder;
    private RankingsRecyclerAdapter rankingsAdapter;

    public static RankingsFragment newInstance() {
        return new RankingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rankingsRecyclerView.setLayoutManager(layoutManager);
        rankingsRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeRankings();
    }

    @Override
    public void onStop() {
        super.onStop();
        rankingsAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeRankings() {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child(PLAYERS)
                .orderByChild(Player.WIN_PLAY_RATIO);

        FirebaseRecyclerOptions<Ranking> options =
                new FirebaseRecyclerOptions.Builder<Ranking>()
                        .setQuery(query, new RankingSnapshotParser())
                        .build();

        rankingsAdapter = new RankingsRecyclerAdapter(options);
        rankingsRecyclerView.setAdapter(rankingsAdapter);

        rankingsAdapter.startListening();
    }
}
