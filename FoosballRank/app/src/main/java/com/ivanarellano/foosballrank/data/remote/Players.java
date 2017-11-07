package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class Players {

    public Map<String, Player> players;

    public Players() {
        // Default constructor required for calls to DataSnapshot.getValue(Players.class)
    }

    public Players(Map<String, Player> players) {
        this.players = players;
    }
}
