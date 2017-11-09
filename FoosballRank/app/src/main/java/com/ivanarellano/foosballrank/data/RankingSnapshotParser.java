package com.ivanarellano.foosballrank.data;

import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.ivanarellano.foosballrank.data.remote.Player;

public class RankingSnapshotParser implements SnapshotParser<Ranking> {
    @Override
    public Ranking parseSnapshot(DataSnapshot snapshot) {
        Player player = snapshot.getValue(Player.class);
        return new Ranking(player.getName(), player.getWinPlayRatio());
    }
}
