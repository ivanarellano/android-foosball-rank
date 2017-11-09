package com.ivanarellano.foosballrank.ui.rankings;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.data.Ranking;

final public class RankingsRecyclerAdapter extends FirebaseRecyclerAdapter<Ranking, RankingsViewHolder> {

    private final static String TAG = RankingsRecyclerAdapter.class.getSimpleName();

    private boolean isDescending;
    private boolean isReversedLayout;

    public RankingsRecyclerAdapter(FirebaseRecyclerOptions<Ranking> options) {
        super(options);

        isDescending = false;
        isReversedLayout = true;
    }

    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankings_row, parent, false);
        return new RankingsViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(final RankingsViewHolder holder, int position, Ranking model) {
        Log.d(TAG, "" + position);
        int rankingIndex;

        if (isDescending && isReversedLayout) {
            rankingIndex = position + 1;
        } else {
            rankingIndex = getItemCount() - position;
        }

        holder.bind(model, rankingIndex);

        /// https://stackoverflow.com/questions/34110497/how-to-implement-a-setonitemclicklistener-firebaserecyclerviewadapter
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, "You clicked on " + holder.getAdapterPosition());
                Log.w(TAG, holder.nameText.getText().toString());
            }
        });
    }

    public void reverseSortOrder(@NonNull LinearLayoutManager layoutManager) {
        isDescending = !isDescending;

        layoutManager.setReverseLayout(!layoutManager.getReverseLayout());
        layoutManager.setStackFromEnd(layoutManager.getReverseLayout());

        isReversedLayout = layoutManager.getReverseLayout();

        Log.d(TAG, "isDescending? : "+ this.isDescending + " .. isReversed? : " + isReversedLayout);
    }
}
