package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class PlayerMatch {

    private int won;
    private Map<String, Boolean> played;

    public PlayerMatch() {
        // Default constructor required for calls to DataSnapshot.getValue(PlayerMatch.class)
    }

    public PlayerMatch(int won, Map<String, Boolean> played) {
        this.won = won;
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public Map<String, Boolean> getPlayed() {
        return played;
    }

    public void setPlayed(Map<String, Boolean> played) {
        this.played = played;
    }
}
