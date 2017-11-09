package com.ivanarellano.foosballrank.ui.rankings;

import android.support.annotation.NonNull;
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

    public interface RankingClickListener {
        void onClick(@NonNull Ranking ranking);
    }

    private RankingClickListener rankingClickListener;
    private boolean isDescending;

    public RankingsRecyclerAdapter(FirebaseRecyclerOptions<Ranking> options) {
        super(options);

        isDescending = true;
    }

    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankings_row, parent, false);
        return new RankingsViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(final RankingsViewHolder holder, final int position, Ranking model) {
        holder.bind(model, isDescending ? position + 1 : getItemCount() - position);

        /// https://stackoverflow.com/questions/34110497/how-to-implement-a-setonitemclicklistener-firebaserecyclerviewadapter
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, "You clicked on " + position);
                Log.w(TAG, holder.nameText.getText().toString());
            }
        });
    }

    @Override
    public Ranking getItem(int position) {
        /// https://github.com/firebase/FirebaseUI-Android/issues/310#issuecomment-247816246
        return super.getItem(isDescending ? getItemCount() - 1 - position : position);
    }

    public void sortOrder(boolean isDescending) {
        this.isDescending = isDescending;
        notifyDataSetChanged();
    }
}
