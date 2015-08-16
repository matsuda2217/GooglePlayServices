package com.example.tt.googleplayservices;

import android.app.Activity;
import android.content.IntentSender;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.util.Set;

public class MainActivity extends ActionBarActivity  implements GoogleApiClient.OnConnectionFailedListener,
                                        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.ServerAuthCodeCallbacks{

    GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this,0);
            } catch (IntentSender.SendIntentException e) {
                googleApiClient.connect();
            }
        }
    }

    @Override
    public CheckResult onCheckServerAuthorization(String s, Set<Scope> set) {
        return null;
    }

    @Override
    public boolean onUploadServerAuthCode(String s, String s1) {
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
