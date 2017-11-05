package com.ivanarellano.foosballrank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.firebase.ui.auth.AuthUI.*;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 15;

    @BindView(R.id.cl_root) ConstraintLayout m_RootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!isSignedIn()) {
            showSignIn();
            Log.d("MainActivity", "Show Sign In");
        } else {
            Log.d("MainActivity", "Already signed in");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Retrieve the ID token
                showSnackbar(R.string.sign_in_success);
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
//                startActivity(SignedInActivity.createIntent(this, response));
//                finish();
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
    }

    private void showSnackbar(@StringRes int id) {
        Snackbar.make(m_RootLayout, id, Snackbar.LENGTH_SHORT).show();
    }

    private void helloFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    private boolean isSignedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    private void showSignIn() {
        AuthUI authUi = getInstance();
        List<AuthUI.IdpConfig> authProviders = Arrays.asList(new IdpConfig.Builder(GOOGLE_PROVIDER).build());
        Intent signInIntent = authUi.createSignInIntentBuilder()
                .setAvailableProviders(authProviders)
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @OnClick(R.id.sign_out)
    protected void signOut () {
        AuthUI authUi = getInstance();
        authUi.signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        showSnackbar(R.string.sign_out_success);
                    }
                });
    }
}
