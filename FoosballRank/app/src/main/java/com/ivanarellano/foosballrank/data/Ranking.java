package com.ivanarellano.foosballrank.data;

import java.util.Objects;

final public class Ranking {

    private final String name;
    private final float rankRatio;

    public Ranking(String name, float rankRatio) {
        this.name = name;
        this.rankRatio = rankRatio;
    }

    public String getName() {
        return name;
    }

    public float getRankRatio() {
        return rankRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking ranking = (Ranking) o;
        return Float.compare(ranking.rankRatio, rankRatio) == 0 &&
                Objects.equals(name, ranking.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rankRatio);
    }
}
