package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
final public class Player {

    public String name;

    public Player() {
        // Default constructor required for calls to DataSnapshot.getValue(Player.class)
    }

    public Player(String name) {
        this.name = name;
    }
}
