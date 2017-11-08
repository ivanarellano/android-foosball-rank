package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

public class Players {

    private Map<String, Player> players;

    public Players() {
        // Default constructor required for calls to DataSnapshot.getValue(Players.class)
    }

    public Players(Map<String, Player> players) {
        this.players = players;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }
}
