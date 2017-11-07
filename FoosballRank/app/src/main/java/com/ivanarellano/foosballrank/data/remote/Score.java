package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
final public class Score {

    public Map<String, Integer> scores;

    public Score() {
        // Default constructor required for calls to DataSnapshot.getValue(Score.class)
    }

    public Score(Map<String, Integer> scores) {
        this.scores = scores;
    }
}
