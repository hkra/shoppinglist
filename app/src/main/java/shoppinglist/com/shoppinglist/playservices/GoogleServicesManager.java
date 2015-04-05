package shoppinglist.com.shoppinglist.playservices;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class GoogleServicesManager implements GoogleApiClient.ConnectionCallbacks {

    public static final String TAG = "GSM";
    private static GoogleServicesManager instance;

    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient.OnConnectionFailedListener mFailedListener;

    private GoogleServicesManager() {
    }

    public void initialize(Context context, GoogleApiClient.OnConnectionFailedListener failListener) {
        mFailedListener = failListener;
        mGoogleApiClient = new GoogleApiClient.Builder(context.getApplicationContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(mFailedListener)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
    }

    public Location getLastLocation() {
        initializedCheck();
        return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    public void initializedCheck() {
        if (mGoogleApiClient == null) {
            throw new IllegalStateException("GoogleApiClient hasn't been initialized. Did you forget to call initialize()?");
        }
    }

    public static GoogleServicesManager getInstance() {
        if (instance == null) {
            instance = new GoogleServicesManager();
        }
        return instance;
    }
}
