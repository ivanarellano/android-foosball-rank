package com.ivanarellano.foosballrank.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.ivanarellano.foosballrank.R;

public class AddMatchActivity extends AppCompatActivity {

    public static void showActivity(@NonNull Activity activity) {
        Intent intent = new Intent(activity, AddMatchActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
    }
}
