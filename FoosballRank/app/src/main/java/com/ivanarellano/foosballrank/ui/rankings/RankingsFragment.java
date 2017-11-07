package com.ivanarellano.foosballrank.ui.rankings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

final public class RankingsFragment extends Fragment {

    public static final String PLAYERS = "players";
    public static final String MATCHES = "matches";
    public static final String PLAYER_MATCHES = "player-matches";

    @BindView(R.id.rv_rankings_list) RecyclerView rankingsRecyclerView;

    private Unbinder unbinder;

    DatabaseReference rootRef;
    DatabaseReference playerMatchesRef;

    public static RankingsFragment newInstance() {
        return new RankingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootRef = FirebaseDatabase.getInstance().getReference();
        playerMatchesRef = rootRef.child(PLAYER_MATCHES);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeRankings() {
        List<Ranking> testData = new ArrayList<Ranking>() {{
            add(new Ranking("Ivan", 4.98f));
            add(new Ranking("Mike", 4.90f));
        }};

        RankingsAdapter adapter = new RankingsAdapter(testData);
        rankingsRecyclerView.setAdapter(adapter);
    }
}
