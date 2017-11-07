package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class Matches {

    public Map<String, Match> matches;

    public Matches() {
        // Default constructor required for calls to DataSnapshot.getValue(Matches.class)
    }

    public Matches(Map<String, Match> matches) {
        this.matches = matches;
    }
}
