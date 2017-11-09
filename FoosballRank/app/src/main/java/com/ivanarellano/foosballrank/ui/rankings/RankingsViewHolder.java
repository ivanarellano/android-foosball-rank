package com.ivanarellano.foosballrank.ui.rankings;

import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

final public class RankingsViewHolder extends RecyclerView.ViewHolder {

    private static final String RATIO_PATTERN = "0.00";

    @BindView(R.id.tv_ranking) TextView rankingText;
    @BindView(R.id.tv_name) TextView nameText;
    @BindView(R.id.tv_ratio) TextView ratioText;

    public RankingsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Ranking ranking, int position) {
        rankingText.setText(String.format(Locale.getDefault(), "%d", position));
        nameText.setText(ranking.getName());

        DecimalFormat df = new DecimalFormat(RATIO_PATTERN);
        df.setRoundingMode(BigDecimal.ROUND_CEILING);
        ratioText.setText(df.format(ranking.getWinPlayRatio()));
    }
}
