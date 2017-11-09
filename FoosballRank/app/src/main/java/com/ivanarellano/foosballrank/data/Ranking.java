package com.ivanarellano.foosballrank.data;

import java.util.Objects;

final public class Ranking {

    private String name;
    private float winPlayRatio;

    public Ranking() {
        // Default constructor required for calls to DataSnapshot.getValue(Ranking.class)
    }

    public Ranking(String name, float rankRatio) {
        this.name = name;
        this.winPlayRatio = rankRatio;
    }

    public String getName() {
        return name;
    }

    public float getWinPlayRatio() {
        return winPlayRatio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWinPlayRatio(float winPlayRatio) {
        this.winPlayRatio = winPlayRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking ranking = (Ranking) o;
        return Float.compare(ranking.winPlayRatio, winPlayRatio) == 0 &&
                Objects.equals(name, ranking.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, winPlayRatio);
    }
}
