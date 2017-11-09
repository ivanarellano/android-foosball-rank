package com.ivanarellano.foosballrank.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
final public class Player {

    public static final String WIN_PLAY_RATIO = "win-play-ratio";

    private String name;
    private float winPlayRatio;

    public Player() {
        // Default constructor required for calls to DataSnapshot.getValue(Player.class)
    }

    public Player(String name, float winPlayRatio) {
        this.name = name;
        this.winPlayRatio = winPlayRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PropertyName(WIN_PLAY_RATIO)
    public float getWinPlayRatio() {
        return winPlayRatio;
    }

    @PropertyName(WIN_PLAY_RATIO)
    public void setWinPlayRatio(float winPlayRatio) {
        this.winPlayRatio = winPlayRatio;
    }
}
