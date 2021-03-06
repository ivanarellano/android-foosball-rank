package com.ivanarellano.foosballrank.ui;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ivanarellano.foosballrank.BuildConfig;
import com.ivanarellano.foosballrank.R;
import com.ivanarellano.foosballrank.ui.rankings.RankingsFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

final public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 15;

    @BindView(R.id.bn_navigation) BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_rankings);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
        }
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (resultCode == RESULT_OK) {
            showSnackbar(R.string.sign_in_success);

            initializeAuth();
        } else {
            if (response == null) {
                // User pressed back button
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }

            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
            }
        }
    }

    private void initializeAuth() {
        if (!isSignedIn()) {
            /// https://firebase.google.com/docs/auth/android/anonymous-auth
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showSnackbar(R.string.sign_in_success);
                            } else {
                                Log.w(MainActivity.class.getCanonicalName(), "signInAnonymously:failure", task.getException());
                            }
                        }
                    });
        } else {
            showSnackbar(R.string.sign_in_success);
        }
    }

    private boolean isSignedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    private void showSignIn() {
        AuthUI authUi = AuthUI.getInstance();
        List<AuthUI.IdpConfig> authProviders = Arrays.asList(
                new AuthUI.IdpConfig.Builder(com.firebase.ui.auth.AuthUI.GOOGLE_PROVIDER).build()
        );
        Intent signInIntent = authUi.createSignInIntentBuilder()
                .setAvailableProviders(authProviders)
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut () {
        AuthUI authUi = AuthUI.getInstance();
        authUi.signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        showSnackbar(R.string.sign_out_success);
                    }
                });
    }

    private void showSnackbar(@StringRes int id) {
        Snackbar.make(findViewById(R.id.cl_root), id, Snackbar.LENGTH_SHORT).show();
    }

    private void showFragment(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_container, fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_rankings:
                    showFragment(RankingsFragment.newInstance());
                    return true;
                case R.id.navigation_activity:
                    showFragment(ActivityFragment.newInstance());
                    return true;
                case R.id.navigation_coin_flip:
                    showFragment(CoinFragment.newInstance());
                    return true;
            }
            return false;
        }
    };
}
