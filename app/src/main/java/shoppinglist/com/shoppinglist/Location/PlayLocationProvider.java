package shoppinglist.com.shoppinglist.Location;

import android.location.Location;

import shoppinglist.com.shoppinglist.playservices.GoogleServicesManager;

public class PlayLocationProvider implements LocationProvider {
    @Override
    public Location getLocation() {
        return GoogleServicesManager.getInstance().getLastLocation();
    }
}
