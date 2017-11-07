package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class PlayerMatches {

    public Map<String, PlayerMatch> player_matches;

    public PlayerMatches() {
        // Default constructor required for calls to DataSnapshot.getValue(PlayerMatches.class)
    }

    public PlayerMatches(Map<String, PlayerMatch> player_matches) {
        this.player_matches = player_matches;
    }
}
