package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
final public class Match {

    private Score score;
    private long timestamp;

    public Match() {
        // Default constructor required for calls to DataSnapshot.getValue(Match.class)
    }

    public Match(Score score, long timestamp) {
        this.score = score;
        this.timestamp = timestamp;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
