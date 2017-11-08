package com.ivanarellano.foosballrank.ui.rankings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;
import com.ivanarellano.foosballrank.data.remote.Player;
import com.ivanarellano.foosballrank.data.remote.PlayerMatch;
import com.ivanarellano.foosballrank.data.remote.PlayerMatches;
import com.ivanarellano.foosballrank.data.remote.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

final public class RankingsFragment extends Fragment {

    public static final String PLAYERS = "players";
    public static final String MATCHES = "matches";
    public static final String PLAYER_MATCHES = "player-matches";

    @BindView(R.id.rv_rankings_list) RecyclerView rankingsRecyclerView;

    private Unbinder unbinder;
    private DatabaseReference playersRef;
    private DatabaseReference playerMatchesRef;
    private RankingsAdapter rankingsAdapter;

    public static RankingsFragment newInstance() {
        return new RankingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        playersRef = rootRef.child(PLAYERS );
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
        //playersRef.addChildEventListener(playersChildListener);
        playersRef.addValueEventListener(playersListener);
//        playerMatchesRef.addValueEventListener(playerMatchesListener);

//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child(PLAYERS)
//                .limitToLast(50);
//
//        FirebaseRecyclerOptions<Players> options =
//                new FirebaseRecyclerOptions.Builder<Players>()
//                        .setQuery(query, Players.class)
//                        .build();

//        rankingsAdapter = new RankingsAdapter(options);
//        rankingsRecyclerView.setAdapter(rankingsAdapter);
    }

    @NonNull
    private List<Ranking> getDemoRankings() {
        return new ArrayList<Ranking>() {{
            add(new Ranking("Ivan", 4.98f));
            add(new Ranking("Mike", 4.90f));
        }};
    }

    @NonNull
    private List<Ranking> getRankings(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<Ranking> rankings = new ArrayList<>();
        PlayerMatches playerMatches = dataSnapshot.getValue(PlayerMatches.class);
//
//        if (playerMatches != null) {
//            rankings.ensureCapacity(playerMatches.player_matches.size());
//
//            for (PlayerMatch match : playerMatches.player_matches.values()) {
//                rankings.add(new Ranking(match.));
//            }
//        }
//
//        rankingsAdapter.populateRankings();

        return rankings;
    }

    private final ChildEventListener playersChildListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            if (dataSnapshot != null) {
                Player player = dataSnapshot.getValue(Player.class);
                Log.d("LOL", player.getName());
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private final ValueEventListener playersListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Map<String, Player> playerMap = new HashMap<>();
            if (dataSnapshot != null) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Player p = snapshot.getValue(Player.class);
                    playerMap.put(snapshot.getKey(), snapshot.getValue(Player.class));

                    Log.d("LOL", p.getName());
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(RankingsFragment.class.getCanonicalName(), "loadPlayers:onCancelled", databaseError.toException());
        }
    };

//    private final ValueEventListener playerMatchesListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            if (dataSnapshot != null) {
//                getRankings(dataSnapshot);
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            Log.w(RankingsFragment.class.getCanonicalName(), "loadPlayerMatches:onCancelled", databaseError.toException());
//        }
//    };
}
