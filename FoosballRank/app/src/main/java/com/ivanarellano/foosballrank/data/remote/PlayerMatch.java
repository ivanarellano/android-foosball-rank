package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
final public class PlayerMatch {

    public int won;
    public Played played;

    public PlayerMatch() {
        // Default constructor required for calls to DataSnapshot.getValue(PlayerMatch.class)
    }

    public PlayerMatch(int won, Played played) {
        this.won = won;
        this.played = played;
    }
}
