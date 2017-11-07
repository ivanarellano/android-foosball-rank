package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class Played {

    public Map<String, Boolean> played;

    public Played() {
        // Default constructor required for calls to DataSnapshot.getValue(Played.class)
    }

    public Played(Map<String, Boolean> played) {
        this.played = played;
    }
}
