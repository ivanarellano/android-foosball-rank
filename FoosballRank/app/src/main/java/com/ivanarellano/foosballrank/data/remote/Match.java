package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
final public class Match {

    public Score score;
    public long timestamp;

    public Match() {
        // Default constructor required for calls to DataSnapshot.getValue(Match.class)
    }

    public Match(Score score, long timestamp) {
        this.score = score;
        this.timestamp = timestamp;
    }
}
