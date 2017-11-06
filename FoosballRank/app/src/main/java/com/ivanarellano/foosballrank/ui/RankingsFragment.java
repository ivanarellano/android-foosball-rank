package com.ivanarellano.foosballrank.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanarellano.foosballrank.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

final public class RankingsFragment extends Fragment {

    private Unbinder unbinder;

    public static RankingsFragment newInstance() {
        return new RankingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
