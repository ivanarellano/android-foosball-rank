package com.ivanarellano.foosballrank.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ivanarellano.foosballrank.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditMatchView extends ConstraintLayout {

    @BindView(R.id.tv_player) TextView playerText;
    @BindView(R.id.til_score) TextInputLayout scoreInput;
    @BindView(R.id.til_name) TextInputLayout nameInput;

    public EditMatchView(Context context) {
        this(context, null);
    }

    public EditMatchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initializeAttrs(attrs);
    }

    private void initializeAttrs(@NonNull AttributeSet attrs) {
        View rootView = inflate(getContext(), R.layout.view_edit_match, this);
        ButterKnife.bind(this, rootView);
    }

    private void setView(int playerNumber, int score, @NonNull String name) {
        playerText.setText(String.format(Locale.getDefault(), "%d", playerNumber));
        scoreInput.getEditText().setText(String.format(Locale.getDefault(), "%d", score));
        nameInput.getEditText().setText(name);
    }
}
